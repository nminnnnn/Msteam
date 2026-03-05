# Hướng dẫn tự chỉnh sửa giao diện (Client)

Giao diện app nằm trong project **Client** (`__DA_CLIENT\__DA_CLIENT`), dùng **Java Swing**. Bạn chỉ cần mở đúng file và sửa màu, chữ, layout hoặc ảnh.

---

## 1. Giao diện nằm ở đâu?

- **Mã nguồn giao diện**: `__DA_CLIENT\__DA_CLIENT\src\view\`
- **Ảnh (icon, nền, avatar)**: `__DA_CLIENT\__DA_CLIENT\src\images\`

### Cấu trúc thư mục `view`

| Thư mục / file | Nội dung |
|----------------|----------|
| `MainUI.java` | Cửa sổ chính, menu trái (Chat, Community, Calendar, User) |
| `Panel_Register.java` | Form điền thông tin sau khi đăng ký (họ tên, email, SĐT, địa chỉ, avatar) |
| `ChatUI/form/` | Đăng nhập, đăng ký, Chat, Home (trò chuyện) |
| `ChatUI/component/` | Khung chat trái/phải, ô nhập tin nhắn, avatar, nút gửi file… |
| `CommunityUI/form/` | Trang Community (Home, Meeting, Post, Member…) |
| `CommunityUI/component/` | Bài viết, meeting, project, nút thêm… |
| `CalendarUI/` | Giao diện lịch |

---

## 2. Chỉnh màu sắc (Color)

Trong file `.java` của màn hình cần sửa, tìm các dòng kiểu:

- `setBackground(new Color(...))` → màu nền
- `setForeground(new Color(...))` → màu chữ

**Ví dụ** (trong `P_Register.java`):

```java
// Đổi màu tiêu đề "Đăng ký"
lbTitle.setForeground(new Color(41, 128, 185));   // RGB: xanh dương

// Đổi màu nền panel
setBackground(new Color(252, 253, 255));          // RGB: trắng xanh nhạt
```

Bạn có thể:
- Đổi số RGB (0–255), ví dụ: `new Color(200, 50, 50)` = đỏ.
- Hoặc dùng hằng: `Color.BLUE`, `Color.WHITE`, `Color.DARK_GRAY`…

---

## 3. Chỉnh chữ (Font, text)

- **Font**: tìm `setFont(new Font(...))`
- **Nội dung chữ**: tìm `setText("...")`

**Ví dụ**:

```java
// Đổi font và cỡ chữ
lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));

// Đổi chữ hiển thị
lbTitle.setText("Đăng ký");
jLabel1.setText("Tên đăng nhập");
```

Font phổ biến trên Windows: `"Segoe UI"`, `"Tahoma"`, `"Arial"`. Số cuối là cỡ chữ (pt).

---

## 4. Chỉnh vị trí, kích thước (layout)

- **Tọa độ + kích thước**: tìm `setBounds(x, y, width, height)`  
  Ví dụ: `setBounds(83, 87, 350, 350)` = cách trái 83, cách trên 87, rộng 350, cao 350 (pixel).
- **Layout dạng lưới / GroupLayout**: trong các file có `GroupLayout`, sửa số `addGap`, `addComponent`, `DEFAULT_SIZE` để chỉnh khoảng cách và thứ tự component.

Màn hình chính và nhiều form dùng `setLayout(null)` rồi đặt từng control bằng `setBounds`.

---

## 5. Thay ảnh (icon, nền, avatar)

Ảnh được load từ **classpath** với đường dẫn bắt đầu bằng `/images/`:

- Trong code thường thấy: `getClass().getResource("/images/...")` hoặc `MainUI.class.getResource("/images/...")`.

**Cấu trúc thư mục ảnh** (tạo nếu chưa có):

- `src/images/background/` — nền, nút lớn (vd: `button_login.png`, `button_register.png`, `avatar.jpg`)
- `src/images/icon/` — icon (vd: `user.png`, `message.png`, `icon_community.png`, `icon_calender.png`, `send.png`, `icon_camera.png`…)
- `src/images/testing/` — ảnh test (vd: `avatar.png`)

**Cách thay ảnh:**

1. Đặt file ảnh mới (cùng tên hoặc tên mới) vào đúng thư mục trong `src/images/`.
2. Nếu dùng **tên mới**: mở file `.java` tương ứng, tìm dòng có `/images/...` và sửa đường dẫn cho đúng tên file.

**Ví dụ** (trong `MainUI.java`):

```java
// Icon nút User
button_user.setIcon(new ImageIcon(..., MainUI.class.getResource("/images/icon/user.png") ...));

// Avatar mặc định
label_user_avatar.setIcon(... MainUI.class.getResource("/images/icon/default.jpeg") ...);
```

Đổi `user.png` hoặc `default.jpeg` bằng file của bạn (hoặc đổi tên file trong code).

---

## 6. Nên mở file nào khi chỉnh từng phần?

| Muốn chỉnh | Mở file (trong `__DA_CLIENT\__DA_CLIENT\src\view\`) |
|------------|-----------------------------------------------------|
| Cửa sổ chính, menu Chat/Community/Calendar/User | `MainUI.java` |
| Màn hình đăng nhập | `ChatUI/form/Login.java`, `ChatUI/form/P_Login.java` |
| Màn hình đăng ký (user + pass) | `ChatUI/form/P_Register.java` |
| Form điền thông tin (họ tên, email, SĐT, avatar) | `Panel_Register.java` |
| Khung chat (danh sách, ô chat, nút gửi) | `ChatUI/component/Chat_Left.java`, `Chat_Right.java`, `Chat_Bottom.java` |
| Trang Community (bài viết, meeting) | `CommunityUI/form/HomeCommu.java`, `CommunityUI/component/Item_post.java`, `NewPost.java` |
| Giao diện lịch | `CalendarUI/CalendarUI.java` |

---

## 7. Sau khi sửa

1. **Lưu file** (Ctrl+S).
2. **Biên dịch lại** (chạy script build của client, ví dụ `build-and-run.ps1` trong thư mục client).
3. **Chạy lại app** để xem thay đổi.

Nếu đổi ảnh: đảm bảo file nằm trong `src/images/...` và đường dẫn trong code trùng với tên file (kể cả phần mở rộng `.png`, `.jpg`…).

---

**Tóm tắt**: Màu/chữ/layout sửa trực tiếp trong từng file `.java` trong `view`; ảnh đặt trong `src/images/` và sửa đường dẫn `/images/...` nếu đổi tên file.
