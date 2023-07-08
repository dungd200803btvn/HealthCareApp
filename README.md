# 1,Giới thiệu qua về nội dung sản phẩm
Đây là 1 app moblile android có tên là Health Care App  
App như một ứng dụng quản lí các sản phẩm dịch vụ của một bệnh viện với các chức năng chính:  
1,Đăng kí tài khoản  
2,Đăng nhập  
3,Chọn gói khám sức khỏe (ứng với chức năng Lab Test)  
4,Mua thuốc(Buy Medicine)  
5,Đăng kí lịch khám với bác sĩ(Find Doctor)  
6,Xem một số lời khuyên để có sức khỏe tốt(healh articles)  
7,Xem chi tiết hóa đơn (Order Detail)  
8,Thoát(Log out)  
# 2,Bối cảnh bắt đầu làm sản phẩm  
-Sau khi có thông báo bắt đầu làm dự án để apply hobo Sun*, đã bắt đầu tạo một dự án mới và thực hiện  
-Lựa chọn đề tài là quản lí bệnh viện vì đây là một vấn đề rất gần gũi,thiết thực,có thể ứng dụng vào thực tế,giúp cho việc khám chữa bệnh của mọi người tiện lợi  hơn nhờ
đặt lịch cũng như chọn thuốc,bác sĩ khám cho hoàn toàn online,không mất thời gian chờ đợi mỗi khi đến bệnh viện  
# 3 Thời gian làm sản phẩm:   
Mất khoảng 10 ngày từ ngày có thông báo :28/6/2023 đến 8/7/2023: bao gồm quá trình lên ý tưởng,phác thảo sơ bộ nội dung,bắt đầu code  
# 4 Vai trò của bản thân:  
Đây là dự án cá nhân,tự mình xây dựng ý tưởng,phác thảo kế hoạch và code từ đầu đến cuối  
# 5 Các chức năng của sản phẩm: giới thiệu và demo chi tiết qua vid tự quay và giải thích  
1,Chức năng đăng kí: cần điền đầy đủ thông tin 4 trường là name,email,password và reset password mới có thể đăng kí thành công   
Những trường hợp sau: điền thiếu 1 trong các trường,mật khẩu và xác nhận mật khẩu không khớp,mật khẩu ít hơn 8 kí tự,ko chứa ít nhất 1 chữ cái,1 chữ số và 1 kí tự  
đặc biệt đều sẽ có thông báo đăng kí không thành công và yêu cầu điền lại đến khi thành công mới thôi  


2,Chức năng đăng nhập: điền username và pass vừa mới đăng kí thành công mới có thể đăng nhập,nhập sai một trong 2 trường đều yêu cầu nhập lại  


3,Màn hình home: chứa 6 thẻ tương ứng với 6 chức năng chính của ứng dụng,bấm vào từng thẻ để trải nghiệm từng tính năng này


4,Lab test: khi bấm vào nút này sẽ hiện ra 5 gói khám chữa bệnh cho người dùng sử dụng,mỗi gói có tên gói và giá tiền  
+Khi bấm vào từng packages sẽ chuyển đến màn hình labtest detail: có thông tin chi tiết của gói đó gồm:tên gói,những dịch vụ được sử dụng,giá tiền,    
trong màn hình này có nút bấm addtoCart để thêm gói này vào giỏ hàng,nút back để quay lại màn hình labtest      
+Sau khi chọn xong có thể bấm vào nút go to cart trên màn hình để xem giỏ hàng hiện tại đã có sản phẩm gì(đây là giỏ dành riêng cho lựa chọn các gói của lab test này)    
+Nút back để quay lại màn hình home


5,Buy Medicine:
Link vid:   
# 6 Điểm nhấn về mặt kỹ thuật:  
- Sử dụng ngôn ngữ Java hoàn toàn tự học,chưa hề được dạy trước trên trường  
- Sử dụng các framework của android như Android Material, SQLite: không cần kết nối với phầm mềm quản trị SQL mà thực hiện hoàn toàn trên app  
- Trong quá trình code cũng gặp một số khó khăn như tìm cách sử dụng SQL Database của Android như thế nào, cách kết nối các màn hình, gửi dữ liệu giữa các màn hình ra sao  
tự tìm hiểu tài liệu trên internet,fix bug thử nghiệm đến lúc thành công mới thôi.  
-Sử dụng công cụ chat GPT là công cụ hỗ trợ hữu ích để fix lỗi cũng như đưa ra code mẫu để tham khảo những phần mình chưa biết  
-Tìm hiểu về Firebase để kết nối firebase với ứng dụng android hiện tại, release file APK của dự án lên firebase  
 để dễ dàng cho mọi người cùng tham gia vào tester sản phẩm cũng như tải về để sử dụng  
# 7 Cách build,run deploy sản phẩm:  
-Gửi cho tôi địa chỉ gmail của bạn,tôi sẽ add tài khoản của bạn vào firebase tương ứng với dự án này,sẽ có mail hướng dẫn bạn cách tải link về và  
cài đặt trên điện thoại của mình ( vì app android nên sẽ chạy được trên những máy có cùng hệ điều hành android,  
trong thời gian tới tôi sẽ phát triển để dự án có thể chạy trên cả nền tảng ios)  
