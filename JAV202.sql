-- 1. Bảng Danh mục (Sửa Name thành VARCHAR)
CREATE TABLE CATEGORY
(
  Category_ID INT NOT NULL,
  Name NVARCHAR(100) NOT NULL,
  Active BIT NOT NULL DEFAULT 1, -- Sử dụng BIT (0/1) cho trạng thái
  PRIMARY KEY (Category_ID)
);

-- 2. Bảng Đồ uống (Sửa Name, Image, Description và Price)
CREATE TABLE DRINKS
(
  Drinks_ID INT NOT NULL,
  Name NVARCHAR(200) NOT NULL,
  Price DECIMAL(18, 2) NOT NULL, -- Giá tiền nên dùng Decimal để chính xác
  Image VARCHAR(MAX),            -- Lưu đường dẫn ảnh hoặc Base64
  Description NVARCHAR(500),
  Active BIT NOT NULL DEFAULT 1,
  Category_ID INT NOT NULL,
  PRIMARY KEY (Drinks_ID),
  FOREIGN KEY (Category_ID) REFERENCES CATEGORY(Category_ID)
);

-- 3. Bảng Người dùng (Sửa Email, Password, Full_name...)
CREATE TABLE USERS
(
  Users_ID INT NOT NULL,
  Email VARCHAR(100) NOT NULL UNIQUE,
  Password VARCHAR(255) NOT NULL,
  Full_name NVARCHAR(200) NOT NULL,
  Phone VARCHAR(15),
  Active BIT NOT NULL DEFAULT 1,
  Role INT NOT NULL, -- 0: Admin, 1: Customer chẳng hạn
  PRIMARY KEY (Users_ID)
);

-- 4. Bảng Hóa đơn (Sửa Code, Created_at)
CREATE TABLE BILLS
(
  Bills_ID INT NOT NULL,
  Code VARCHAR(20) NOT NULL,
  Created_at DATETIME DEFAULT GETDATE(),
  Total DECIMAL(18, 2) NOT NULL,
  Status INT NOT NULL, -- 0: Chờ xử lý, 1: Đã thanh toán, 2: Hủy
  Users_ID INT NOT NULL,
  PRIMARY KEY (Bills_ID),
  FOREIGN KEY (Users_ID) REFERENCES USERS(Users_ID)
);

-- 5. Bảng Chi tiết hóa đơn
CREATE TABLE BILL_DETAILS
(
  Price DECIMAL(18, 2) NOT NULL,
  Quantity INT NOT NULL,
  Drinks_ID INT NOT NULL,
  Bills_ID INT NOT NULL,
  PRIMARY KEY (Drinks_ID, Bills_ID),
  FOREIGN KEY (Drinks_ID) REFERENCES DRINKS(Drinks_ID),
  FOREIGN KEY (Bills_ID) REFERENCES BILLS(Bills_ID)
);


-- Thêm danh mục
INSERT INTO CATEGORY (Category_ID, Name, Active) VALUES 
(1, N'Cà phê', 1),
(2, N'Trà trái cây', 1),
(3, N'Đá xay', 1),
(4, N'Sinh tố', 1),
(5, N'Nước ép', 1),
(6, N'Trà sữa', 1),
(7, N'Thức uống nóng', 1);

-- Thêm đồ uống
INSERT INTO DRINKS (Drinks_ID, Name, Price, Image, Description, Active, Category_ID) VALUES 
(1, N'Bạc xỉu', 29000, 'bacxiu.jpg', N'Cà phê sữa nhiều sữa', 1, 1),
(2, N'Trà đào cam sả', 45000, 'tradao.jpg', N'Trà đào tươi mát', 1, 2),
(3, N'Matcha đá xay', 55000, 'matcha.jpg', N'Vị trà xanh Nhật Bản', 1, 3),
(4, N'Sinh tố bơ', 50000, 'sinhtobo.jpg', N'Bơ xay mịn béo ngậy', 1, 4),
(5, N'Nước ép cam', 40000, 'nuocepcam.jpg', N'Cam tươi nguyên chất', 1, 5),
(6, N'Trà sữa trân châu', 48000, 'trasua.jpg', N'Trà sữa truyền thống', 1, 6),
(7, N'Cà phê đen nóng', 25000, 'capheden.jpg', N'Cà phê nguyên chất', 1, 7),
(8, N'Sinh tố dâu', 52000, 'sinhtodau.jpg', N'Dâu tươi xay', 1, 4),
(9, N'Nước ép dưa hấu', 38000, 'duahau.jpg', N'Mát lạnh giải nhiệt', 1, 5);

-- Thêm người dùng
INSERT INTO USERS (Users_ID, Email, Password, Full_name, Phone, Active, Role) VALUES 
(1, 'admin@gmail.com', '123456', N'Quản trị viên', '0912345678', 1, 0),
(2, 'khachhang1@gmail.com', '123456', N'Nguyễn Văn A', '0987654321', 1, 1),
(3, 'nhanvien1@gmail.com', '123456', N'Trần Văn B', '0901111111', 1, 0),
(4, 'khachhang2@gmail.com', '123456', N'Lê Thị C', '0902222222', 1, 1),
(5, 'khachhang3@gmail.com', '123456', N'Phạm Văn D', '0903333333', 1, 1);

-- Thêm hóa đơn
INSERT INTO BILLS (Bills_ID, Code, Created_at, Total, Status, Users_ID) VALUES 
(1, 'HD001', '2024-05-20 10:30:00', 74000, 1, 2);
(2, 'HD002', '2024-05-21 09:15:00', 90000, 1, 4),
(3, 'HD003', '2024-05-22 14:20:00', 75000, 1, 5),
(4, 'HD004', '2024-05-23 18:45:00', 25000, 0, 2),
(5, 'HD005', '2024-05-24 20:10:00', 52000, 2, 3);

INSERT INTO BILL_DETAILS (Price, Quantity, Drinks_ID, Bills_ID) VALUES 
(29000, 1, 1, 1),
(45000, 1, 2, 1),
(45000, 2, 2, 2),
(25000, 1, 7, 3),
(50000, 1, 4, 3),
(25000, 1, 7, 4),
(52000, 1, 8, 5);