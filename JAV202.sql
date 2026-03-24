CREATE DATABASE polycoffee;
GO
USE polycoffee;
GO

-- ================= CATEGORY =================
CREATE TABLE CATEGORY
(
  Category_ID INT IDENTITY(1,1) PRIMARY KEY,
  Name NVARCHAR(100) NOT NULL,
  Active BIT NOT NULL DEFAULT 1
);

-- ================= DRINKS =================
CREATE TABLE DRINKS
(
  Drinks_ID INT IDENTITY(1,1) PRIMARY KEY,
  Name NVARCHAR(200) NOT NULL,
  Price INT NOT NULL, -- FIX: đổi từ DECIMAL -> INT
  Image VARCHAR(255),
  Description NVARCHAR(500),
  Active BIT NOT NULL DEFAULT 1,
  Category_ID INT NOT NULL,
  FOREIGN KEY (Category_ID) REFERENCES CATEGORY(Category_ID)
);

-- ================= USERS =================
CREATE TABLE USERS
(
  Users_ID INT IDENTITY(1,1) PRIMARY KEY,
  Email VARCHAR(100) NOT NULL UNIQUE,
  Password VARCHAR(255) NOT NULL,
  Full_name NVARCHAR(200) NOT NULL,
  Phone VARCHAR(15),
  Active BIT NOT NULL DEFAULT 1,
  Role BIT NOT NULL DEFAULT 0 -- FIX: 1=MANAGER, 0=EMPLOYEE
);

-- ================= BILLS =================
CREATE TABLE BILLS
(
  Bills_ID INT IDENTITY(1,1) PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Created_at DATETIME DEFAULT GETDATE(),
  Total INT NOT NULL DEFAULT 0,
  Status INT NOT NULL DEFAULT 0, -- 0: WAITING
  Users_ID INT NOT NULL,
  FOREIGN KEY (Users_ID) REFERENCES USERS(Users_ID)
);

-- ================= BILL DETAILS =================
CREATE TABLE BILL_DETAILS
(
  Bills_ID INT NOT NULL,
  Drinks_ID INT NOT NULL,
  Quantity INT NOT NULL,
  Price INT NOT NULL,
  PRIMARY KEY (Drinks_ID, Bills_ID),
  FOREIGN KEY (Drinks_ID) REFERENCES DRINKS(Drinks_ID),
  FOREIGN KEY (Bills_ID) REFERENCES BILLS(Bills_ID) ON DELETE CASCADE
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
-- Thêm 50 đồ uống mẫu đa dạng cho PolyCoffee
INSERT INTO DRINKS (Drinks_ID, Name, Price, Image, Description, Active, Category_ID) VALUES 
-- Danh mục 1: Cà phê (10 món)
(1, N'Bạc xỉu', 29000, 'bacxiu.jpg', N'Cà phê sữa nhiều sữa đặc', 1, 1),
(2, N'Cà phê đen đá', 25000, 'den_da.jpg', N'Cà phê pha phin truyền thống', 1, 1),
(3, N'Cà phê sữa đá', 29000, 'sua_da.jpg', N'Cà phê phin kết hợp sữa đặc', 1, 1),
(4, N'Cà phê cốt dừa', 35000, 'cot_dua.jpg', N'Cà phê xay cùng nước cốt dừa béo ngậy', 1, 1),
(5, N'Cà phê muối', 35000, 'cafe_muoi.jpg', N'Lớp kem muối béo mặn độc đáo', 1, 1),
(6, N'Americano đá', 30000, 'americano.jpg', N'Cà phê Espresso pha loãng thanh mát', 1, 1),
(7, N'Latte đá', 40000, 'latte.jpg', N'Espresso kết hợp sữa tươi thanh trùng', 1, 1),
(8, N'Cappuccino', 45000, 'cappuccino.jpg', N'Cà phê Ý với lớp bọt sữa dày', 1, 1),
(9, N'Cold Brew', 40000, 'cold_brew.jpg', N'Cà phê ủ lạnh 24h', 1, 1),
(10, N'Cold Brew Cam Sả', 45000, 'cold_brew_cam.jpg', N'Cà phê ủ lạnh kết hợp vị cam sả', 1, 1),

-- Danh mục 2: Trà trái cây (10 món)
(11, N'Trà đào cam sả', 45000, 'tra_dao.jpg', N'Trà đào tươi mát best seller', 1, 2),
(12, N'Trà vải nhiệt đới', 45000, 'tra_vai.jpg', N'Trà đen kết hợp trái vải ngâm', 1, 2),
(13, N'Trà dâu tằm', 40000, 'tra_dau_tam.jpg', N'Vị chua ngọt thanh mát', 1, 2),
(14, N'Trà xoài macchiato', 50000, 'tra_xoai.jpg', N'Trà xoài tươi kèm kem phô mai', 1, 2),
(15, N'Trà thanh đào', 45000, 'thanh_dao.jpg', N'Trà đào miếng giòn sần sật', 1, 2),
(16, N'Trà bưởi mật ong', 45000, 'tra_buoi.jpg', N'Trà bưởi hồng chua ngọt dịu nhẹ', 1, 2),
(17, N'Trà chanh dây', 35000, 'chanh_day.jpg', N'Giải nhiệt mùa hè cực đã', 1, 2),
(18, N'Trà ổi hồng', 45000, 'oi_hong.jpg', N'Vị ổi hồng thơm lừng', 1, 2),
(19, N'Trà táo bạc hà', 40000, 'tao_bac_ha.jpg', N'Trà táo xanh the mát', 1, 2),
(20, N'Lục trà lài', 30000, 'luc_tra.jpg', N'Lục trà ướp hoa nhài thanh lọc', 1, 2),

-- Danh mục 3: Đá xay (7 món)
(21, N'Matcha đá xay', 55000, 'matcha_dx.jpg', N'Trà xanh Nhật Bản đá xay kem tươi', 1, 3),
(22, N'Socola đá xay', 50000, 'socola_dx.jpg', N'Đậm vị cacao nguyên chất', 1, 3),
(23, N'Cà phê đá xay', 45000, 'cafe_dx.jpg', N'Cà phê xay nhuyễn mát lạnh', 1, 3),
(24, N'Caramel Macchiato đá xay', 55000, 'caramel_dx.jpg', N'Vị béo thơm của caramel', 1, 3),
(25, N'Việt quất đá xay', 55000, 'viet_quat_dx.jpg', N'Đá xay trái việt quất chua ngọt', 1, 3),
(26, N'Cookies Cream', 55000, 'cookies_dx.jpg', N'Bánh Oreo xay cùng sữa tươi', 1, 3),
(27, N'Khoai môn đá xay', 50000, 'khoai_mon_dx.jpg', N'Vị khoai môn bùi bùi thơm béo', 1, 3),

-- Danh mục 4: Sinh tố (6 món)
(28, N'Sinh tố bơ', 50000, 'st_bo.jpg', N'Bơ Đắk Lắk xay mịn béo ngậy', 1, 4),
(29, N'Sinh tố dâu tây', 52000, 'st_dau.jpg', N'Dâu tây Đà Lạt tươi mát', 1, 4),
(30, N'Sinh tố mãng cầu', 45000, 'st_mang_cau.jpg', N'Mãng cầu chua ngọt dễ uống', 1, 4),
(31, N'Sinh tố xoài', 45000, 'st_xoai.jpg', N'Xoài cát Hòa Lộc chín mọng', 1, 4),
(32, N'Sinh tố đu đủ', 40000, 'st_du_du.jpg', N'Đu đủ tươi tốt cho sức khỏe', 1, 4),
(33, N'Sinh tố dừa sáp', 60000, 'st_dua_sap.jpg', N'Đặc sản dừa sáp béo thơm', 1, 4),

-- Danh mục 5: Nước ép (6 món)
(34, N'Nước ép cam', 40000, 'ep_cam.jpg', N'Cam sành vắt nguyên chất', 1, 5),
(35, N'Nước ép dưa hấu', 38000, 'ep_dua_hau.jpg', N'Mát lạnh giải nhiệt nhanh chóng', 1, 5),
(36, N'Nước ép cà rốt', 40000, 'ep_ca_rot.jpg', N'Bổ sung vitamin A', 1, 5),
(37, N'Nước ép thơm', 38000, 'ep_thom.jpg', N'Nước ép dứa chua ngọt', 1, 5),
(38, N'Nước ép táo', 45000, 'ep_tao.jpg', N'Táo nhập khẩu ép chậm', 1, 5),
(39, N'Nước ép cần tây dứa', 50000, 'ep_can_tay.jpg', N'Thức uống detox giữ dáng', 1, 5),

-- Danh mục 6: Trà sữa (6 món)
(40, N'Trà sữa trân châu', 48000, 'ts_tran_chau.jpg', N'Trà sữa truyền thống full topping', 1, 6),
(41, N'Sữa tươi trân châu đường đen', 45000, 'sua_tuoi_dd.jpg', N'Sữa tươi thanh trùng và đường đen', 1, 6),
(42, N'Trà sữa Oolong nướng', 50000, 'ts_oolong.jpg', N'Vị trà nướng đậm đà', 1, 6),
(43, N'Trà sữa Thái xanh', 40000, 'ts_thai_xanh.jpg', N'Trà xanh Thái Lan thơm mát', 1, 6),
(44, N'Trà sữa Thái đỏ', 40000, 'ts_thai_do.jpg', N'Trà đỏ Thái Lan béo ngậy', 1, 6),
(45, N'Trà sữa hoa nhài', 45000, 'ts_hoa_nhai.jpg', N'Trà sữa ướp hương nhài dịu', 1, 6),

-- Danh mục 7: Thức uống nóng (5 món)
(46, N'Cà phê đen nóng', 25000, 'den_nong.jpg', N'Cà phê nguyên chất đậm vị', 1, 7),
(47, N'Trà gừng mật ong', 35000, 'tra_gung.jpg', N'Làm ấm cơ thể ngày lạnh', 1, 7),
(48, N'Trà hoa cúc', 35000, 'hoa_cuc.jpg', N'Giúp ngủ ngon an thần', 1, 7),
(49, N'Cacao nóng', 40000, 'cacao_nong.jpg', N'Cacao nguyên chất pha sữa', 1, 7),
(50, N'Sữa tươi nóng', 25000, 'sua_nong.jpg', N'Sữa bò thanh trùng đánh bọt nóng', 1, 7);

-- Thêm người dùng
INSERT INTO USERS (Users_ID, Email, Password, Full_name, Phone, Active, Role) VALUES 
(1, 'admin@gmail.com', '123456', N'Quản trị viên', '0912345678', 1, 0),
(2, 'khachhang1@gmail.com', '123456', N'Nguyễn Văn A', '0987654321', 1, 1),
(3, 'nhanvien1@gmail.com', '123456', N'Trần Văn B', '0901111111', 1, 0),
(4, 'khachhang2@gmail.com', '123456', N'Lê Thị C', '0902222222', 1, 1),
(5, 'khachhang3@gmail.com', '123456', N'Phạm Văn D', '0903333333', 1, 1);

-- Thêm hóa đơn
INSERT INTO BILLS (Bills_ID, Code, Created_at, Total, Status, Users_ID) VALUES 
(1, 'HD001', '2024-05-20 10:30:00', 74000, 1, 2),
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

delete from BILL_DETAILS
delete from DRINKS

select * from DRINKS

