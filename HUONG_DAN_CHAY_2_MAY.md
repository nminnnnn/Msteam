# Hướng dẫn chạy TEAMS trên 2 laptop khác nhau

## Tổng quan

- **Máy 1 (Server)**: Chạy server + MySQL + giao diện quản lý server
- **Máy 2 (Client)**: Chạy client TEAMS, kết nối tới máy 1 qua mạng LAN

Cả 2 máy cần **cùng mạng** (cùng WiFi hoặc cùng dây mạng qua switch/router).

---

## Phần 1: Chuẩn bị MÁY SERVER (Laptop 1)

### 1.1. Cài đặt môi trường

- **JDK 11 hoặc 17** (Oracle / OpenJDK)
- **Docker Desktop** (để chạy MySQL) hoặc MySQL cài sẵn
- **IDE Java** (IntelliJ / Eclipse / NetBeans) hoặc dùng Cursor + terminal

### 1.2. Chạy MySQL (Docker)

Mở PowerShell trên máy server:

```powershell
docker start msteam-mysql
```

Nếu chưa tạo container, chạy trước:

```powershell
docker run -d --name msteam-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=dacs1_data -p 3307:3306 mysql:8
```

### 1.3. Import database (chỉ làm 1 lần)

```powershell
type "d:\year4\LTM\DACS4_SourceCode\__DA_CLIENT\__DA_CLIENT\src\other\preparing2.sql" | docker exec -i msteam-mysql mysql -uroot
```

### 1.4. Kiểm tra `DatabaseConnection.java` (Server)

File: `__DA_SERVER\__DA_SERVER\src\connection\DatabaseConnection.java`

Đảm bảo:

- `databaseName = "dacs1_data"`
- `url = "jdbc:mysql://localhost:3307/" + databaseName` (hoặc port bạn đã map)
- `username = "root"`, `password = ""` (hoặc mật khẩu bạn đặt)

### 1.5. Lấy IP của máy server

Trên máy server, mở PowerShell:

```powershell
ipconfig
```

Tìm dòng **IPv4 Address** của adapter đang dùng (WiFi hoặc Ethernet), ví dụ: `192.168.1.100`

**Ghi lại IP này** – máy client sẽ dùng để kết nối.

### 1.6. Mở Firewall cho port 1610

Trên máy server, mở PowerShell **chạy với quyền Administrator**:

```powershell
netsh advfirewall firewall add rule name="TEAMS Server 1610" dir=in action=allow protocol=TCP localport=1610
```

(Tùy chọn – nếu cần test Meeting với âm thanh/share màn hình, có thể tạm tắt firewall cho mạng riêng khi test)

### 1.7. Chạy Server

1. Mở project `__DA_SERVER\__DA_SERVER` trong IDE
2. Thêm JAR: `mysql-connector-j`, `org.json`, `miglayout`
3. Chạy class **`view.Main`**
4. Kiểm tra console:
   - `Kết nối thành công vs database dacs1_data`
   - `Server has started on port: 1610`
5. Cửa sổ **SERVER** hiện ra

---

## Phần 2: Chuẩn bị MÁY CLIENT (Laptop 2)

### 2.1. Cài đặt môi trường

- **JDK 11 hoặc 17**
- **IDE Java** hoặc Cursor

### 2.2. Cấu hình IP Server

Mở file: `__DA_CLIENT\__DA_CLIENT\src\service\Service.java`

Tìm dòng:

```java
private static final String SERVER_IP = "localhost";
```

Đổi thành **IP của máy server** (ví dụ `192.168.1.100`):

```java
private static final String SERVER_IP = "192.168.1.100";
```

Lưu file.

### 2.3. Chạy Client

1. Mở project `__DA_CLIENT\__DA_CLIENT` trong IDE
2. Thêm JAR: `org.json`, `flatlaf` (nếu cần)
3. Chạy class **`view.MainUI`**
4. Màn hình **Login/Register** hiện ra

---

## Phần 3: Test đồng bộ giữa 2 máy

### 3.1. Đăng ký & đăng nhập

- Trên **máy client**: Đăng ký user `user1` / `pass1`, sau đó đăng nhập
- Trên **máy server**: Cửa sổ SERVER sẽ hiện log "Client connected", "Login successfully" (nếu có)

### 3.2. Test Chat (cần 2 client)

- **Máy client**: Chạy thêm 1 instance `MainUI` (Run lần 2), đăng ký `user2` / `pass2`, login
- Hoặc dùng **máy thứ 3** (nếu có) chạy client, đăng ký `user2`
- Từ `user1` gửi tin nhắn cho `user2` → `user2` nhận được

### 3.3. Test Community

- Tạo project mới từ `user1`
- Thêm `user2` làm member (nếu có chức năng)
- Đăng bài post → cả 2 user thấy bài
- Tạo Progress → đồng bộ

### 3.4. Test Calendar

- Tạo sự kiện từ `user1`
- Đóng client, mở lại → sự kiện vẫn còn (lưu trên server)

### 3.5. Test Meeting (tùy chọn)

- Cần mic/loa hoạt động
- Cả 2 user join cùng 1 meeting trong project
- Bật mic, share màn hình để kiểm tra

---

## Phần 4: Xử lý lỗi thường gặp

### Client không kết nối được server

- Kiểm tra 2 máy **cùng mạng** (ping từ client: `ping 192.168.1.100`)
- Kiểm tra **firewall** máy server đã mở port 1610
- Kiểm tra **SERVER_IP** trong `Service.java` (client) đúng IP máy server

### Server báo lỗi kết nối database

- Kiểm tra MySQL container đang chạy: `docker ps`
- Kiểm tra `DatabaseConnection.java`: url, port, user, pass

### Meeting không nghe/không share được

- Firewall có thể chặn UDP; thử tạm tắt firewall khi test
- Meeting dùng UDP port động (meetingId, meetingId+1000)

---

## Tóm tắt nhanh

| Bước | Máy Server | Máy Client |
|------|------------|------------|
| 1 | Cài JDK, Docker, IDE | Cài JDK, IDE |
| 2 | `docker start msteam-mysql` | - |
| 3 | Import SQL (1 lần) | - |
| 4 | Chạy `view.Main` | - |
| 5 | Ghi IP (vd: 192.168.1.100) | Sửa `SERVER_IP` trong Service.java |
| 6 | Mở firewall port 1610 | Chạy `view.MainUI` |
