# Hướng dẫn dùng Cursor: thêm JAR, biên dịch và chạy

## Bước 1: Tải các thư viện JAR (chỉ làm 1 lần)

Mở **Terminal** trong Cursor (Ctrl+` hoặc View → Terminal), chạy:

```powershell
cd d:\year4\LTM\DACS4_SourceCode
.\download-jars.ps1
```

Script sẽ tải JAR vào:
- **Server**: `__DA_SERVER\__DA_SERVER\lib\`
- **Client**: `__DA_CLIENT\__DA_CLIENT\lib\`

Nếu báo "cannot be loaded because running scripts is disabled", chạy trước:
```powershell
Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
```
rồi chạy lại `.\download-jars.ps1`.

---

## Bước 2: Chạy SERVER

### 2.1. Bật MySQL (Docker)

Trước khi chạy Server, cần MySQL đang chạy:

```powershell
docker start msteam-mysql
docker ps
```

(Nếu chưa tạo container, xem lại file `HUONG_DAN_CHAY_2_MAY.md` phần Docker.)

### 2.2. Biên dịch và chạy Server

Mở Terminal, chạy:

```powershell
cd d:\year4\LTM\DACS4_SourceCode\__DA_SERVER\__DA_SERVER
.\build-and-run.ps1
```

- Lần đầu: script **biên dịch** toàn bộ mã nguồn rồi **chạy** `view.Main`.
- Cửa sổ **SERVER** sẽ mở. Console in: `Kết nối thành công vs database ...` và `Server has started on port: 1610`.

Nếu lỗi **Communications link failure** / **Connection refused** → kiểm tra MySQL đã chạy và đúng port trong `DatabaseConnection.java` (vd: 3307).

---

## Bước 3: Chạy CLIENT

Mở **Terminal mới** (để Server chạy ở terminal cũ), chạy:

```powershell
cd d:\year4\LTM\DACS4_SourceCode\__DA_CLIENT\__DA_CLIENT
.\build-and-run.ps1
```

- Script biên dịch Client rồi chạy `view.MainUI`.
- Cửa sổ **Login/Register** TEAMS sẽ hiện. Đảm bảo Server đã chạy trước khi mở Client.

---

## Chỉ biên dịch, không chạy

- **Server**: trong `__DA_SERVER\__DA_SERVER` chạy `.\build-and-run.ps1` vẫn sẽ chạy luôn. Nếu chỉ muốn build, mở file `build-and-run.ps1`, xóa hoặc comment 2 dòng cuối (Set-Location $out; & java ...).
- **Client**: tương tự trong `__DA_CLIENT\__DA_CLIENT`.

---

## Các thư viện đã được tải

| Project | JAR trong `lib\` |
|--------|-------------------|
| **Server** | mysql-connector-j, json, miglayout-swing, miglayout-core, timingframework |
| **Client** | json, flatlaf |

---

## Lỗi thường gặp

- **"running scripts is disabled"** → chạy `Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned`.
- **"Communications link failure"** (Server) → bật MySQL: `docker start msteam-mysql`, kiểm tra port trong `DatabaseConnection.java`.
- **"Connection refused"** (Client) → chạy Server trước; nếu chạy 2 máy thì sửa `SERVER_IP` trong `__DA_CLIENT\__DA_CLIENT\src\service\Service.java`.
- **Lỗi biên dịch thiếu class** → chạy lại `.\download-jars.ps1` và kiểm tra thư mục `lib` có đủ file .jar.
