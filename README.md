# Đề tài: quản lý thi trắc nghiệm trong mạng lan
# 1.Thành viên nhóm
	- B16DCCN034	Trần Đức Chuyên		chuyentran282@gmail.com
	- B16DCCB274	Nguyễn Thị Phương	nguyenphuong27798@gmail.com
	- B16DCCN151	Lê Thị Hoa		lehoa98ptit@gmail.com

# 2. Các chức năng chính:
 ## 2.1 Client 
 - Thi trắc nghiệm: client đăng nhập vào hệ thống, lấy đề thi từ server và làm trong 1 thời gian nhất định; người dùng làm lần lượt các câu hỏi trong bộ câu hỏi, làm theo thứ tự lần lượt từng câu một đến khi hết câu hỏi hoặc hết thời gian làm bài. Trong thời gian làm bài thì người dùng không thể thoát khỏi màn hình ứng dụng làm bài cũng như không thể truy cập sang ứng dụng hay tiến trình khác mặc dù ứng dụng đó có thể được mở và hiện lên task bar đối với hệ điều hành Windows. Sau khi hết thời gian làm bài hoặc hoàn thành xong người dùng sẽ nhận được kết quả số câu trả lời đúng của mình. Trong trường hợp người dùng chưa hoàn thành xong bài khi hết thời gian thì các câu hỏi chưa hoàn thành sẽ không tiếp tục hiển thị

 ## 2.2 Server:
 - Đối với server, người dùng trên server sẽ tạo tài khoản cho client sử dụng, tạo đề thi, soạn bộ câu hỏi để lưu vào database. Khi người dùng bật server thì phía client mới có thể đăng nhập vào làm bài. 
- Với mỗi client sẽ lưu thông tin vị trí máy trong sơ đồ theo thứ tự từ 1 đến n. Khi đó máy của client có số thứ tự là bao nhiêu thì sẽ hiện thị tại dòng tương ứng trong bảng thông tin phía server.
- Người dùng có thể thay đổi vị trí máy của client thông qua file **ipserver.conf**. Nội dung của file này gồm dòng 1 là vị trí máy của client, dòng thứ 2 là địa chỉ ip của máy client.
- Mỗi khi người dùng phía client đăng nhập, trên giao diện server sẽ hiển thị thông tin vị trí máy, địa chỉ ip, trạng thái: đang đăng nhập hay chưa đăng nhập, tên người dùng, thời gian bắt đầu làm bài, thời gian làm bài còn lại, kết quả khi hoàn thành xong.
- Các chức năng quản lý của server bao gồm:
	- Đăng nhập từ server cho máy client
	- Đăng xuất từ server cho máy client
	- Shutdown máy client
	- Restart máy client
	- Theo dõi mà hình máy client(Desktop)
	- Theo dõi các tiến trình người dùng trên máy client; có thể đóng các tiến trình này.
	- Điều khiển máy client khi sử dụng chức năng desktop

# 3. Các kỹ thuật sử dụng
- Ứng dụng được viết trên netbean 8.2 bằng ngôn ngữ JAVA, giao diện swing, sử dụng chủ yếu TCP socket để kết nối cũng như truyền dữ liệu giữa các máy client với server
- Ngoài ra ứng dụng còn sử dụng thêm các class như Robot để điều khiển từ xa, ngăn chặn thoát màn hình khi làm bài; Java 2D để tạo hình ảnh màn hình người dùng; thread tạo các luồng thực hiện các chức năng khác nhau.
- Môi trường sử dụng trên ứng dụng là: Windows

# 4. Các cài đặt sử dụng ứng dụng:
 ## 4.1 Đối với máy client
 - Cài đặt project **Client**, cập nhật địa chỉ ip server trên dòng 26 của file **Csmclient/Csmclient.java**
 - Sửa thông tin file **ipserver.conf** dòng 1 là vị trí máy, dòng 2 là địa chỉ ip của máy client.
 - Set file Run của project là file **Csmclient/Csmclient.java**
 - Thông tin user/pass dùng để test hiện tại là admin/admin
 - Build project and run
 - Lưu ý: khi chọn chức năng làm bài, chỉ có thể thoát bằng cách click CLOSE.

 ## 4.2 Đối với máy server
 - Cài đặt project **Server**
 - Set file run của project là **csmserver/CsmServer.java**
 - Build and run project

# 5. Chi tiết các package server:
 ## 5.1 Package csmserver
 - **CSMServer.java**: file main project
 - **CsmserverGui.java**: giao diện của project.
 	- Tại đây sẽ khởi tạo 1 server socket cho client kết nối đến bằng cách gọi đến 1 thread được lập trình trong 				**action/CsmConnection.java**
 	- Mỗi khi button đăng xuất, đăng nhập, desktop, ứng dụng clicked: sẽ gọi đến 1 thread **Commands** được lập trình 			trong **command/Commands.java**
 - **ProcessListGui.java**:giao diện hiển thị danh sách các tiến trình
 ## 5.2 Package action
 - **CsmCOnnection.java**: tạo server socket cho phép client kết nối đến; tiếp theo đó tạo ra 1 thread **LoginFromClient** để lắng nghe 
   và thực hiện các yêu cầu đến từ client.
 - **LoginFromClient.java**: chứa thread có tác dụng check đăng nhập từ phía client, lấy thông tin client để update vào table giao diện   của server; thực hiện gửi gói câu hỏi xuống client và nhận kết quả làm bài từ client; đồng thời tạo ra 1 thread đếm ngược thời gian     khi client bắt đầu làm bài bằng thread **SetTime** 
 - **SetTime.java**: tạo thread với mục đích đếm ngược thời gian khi client bắt đầu làm bài
 ## 5.3 Package command
  - **Commands.java**: khi server thực hiện các chức năng như đăng xuất, đăng nhập, desktop,... lúc này nó đóng vai trò là 1 *client* 
  gửi các yêu cầu thực hiện đến *server* do chính máy client tạo ra. Lúc này thread commands sẽ kết nối đến *server* của máy client và 
  gửi các đoạn câu lệnh để máy client có thể biên dịch và thực hiện theo đúng yêu cầu.
  Với chức năng desktop thì sẽ tiến hành gọi đến giao diện **RemoteServerGui.java**
## 5.4 Package remote
 - **RemoteServerGui.java**: chứa giao diện để hiển thị màn hình client
 - **RemoteServerInit.java**: tiếp tục tạo 1 server socket dành riêng cho việc gửi nhận hình ảnh màn hình.
 - **ClientHandler.java**: nhận các object là hỉnh ảnh màn hình đc gửi từ client lên
 - **ClientCommandsSender.java**: gửi các sự kiện liên quan đến chuột xuống client.
 
  # 6. Chi tiết các package Client
  ## 6.1 csmclient
   - **Csmclient.java**: đọc thông tin từ file **ipserver.conf** đồng thời kết nối đến server socket đăng nhập và làm bài thi do 
   **CsmCOnnection.java** phía server cung cấp.
  ## 6.2 action
  - **RecvConnectionFromServer.java**: khởi tạo 1 server của client phục vụ các yêu cầu từ phía **Commands.java** của server.
  - **CommandFromServer.java**: nhận và giải mã các yêu cầu từ server và thực hiện như đăng nhập đăng xuất,...
  - **SetTime.java**: thread dùng để đếm ngược thời gian làm bài.
  ## 6.3 security
  - **WindowsSecurity.java**: tạo ra thread ngăn chặn việc truy cập các ứng dụng khác trong khi làm bài thi
  ## 6.4 remote
  - **RemoteClientInit.java**: kết nối đến *server* thứ 2 của Server được tạo ra từ **RemoteServerInit.java** ở phía máy server.
  - **ScreenSpyer.java**: gửi các ảnh màn hình theo dạng object đến server
  - **ServerDelegate.java**: nhận các sự kiện chuột từ phía server và giả lập thực hiện các sự kiện đó thông qua Robot
  ## 6.5 HienThi
  - Chứa các giao diện liên quan đến việc làm bài thi
  
  # 7. Hướng phát triển:
  - Về phía client sẽ xây dựng 1 danh sách các ứng dụng đi kèm được phép truy cập khi làm bài thi.
  - Xây dựng các tiêu chí cảnh báo đến server khi client cố tình thực hiện các hành vi không được phép
  - Server phát triển tiếp việc đọc lịch sử trình duyệt máy client
  - Xây dựng thêm nhiều chế độ thi như: Server tạo bài thi theo thời gian cố định để các máy client làm cùng 1 lúc.
