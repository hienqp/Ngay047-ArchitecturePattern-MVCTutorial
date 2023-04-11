__MVC (Model - View - Controller)__

- __MVC__ là viết tắt của 3 chữ: Model – View – Controller.

	- 3 chữ này đại diện cho 3 layer. Các lập trình viên sẽ phải hiểu nguyên tắc của từng layer, để mà tạo ra các lớp tương ứng với các layer đó. Lớp nào thuộc layer nào sẽ tuân thủ theo nguyên tắc của layer đó. Việc đặt tên lớp hay package/directory có thể bao hàm cả tên layer để dễ hiểu và dễ quản lý. Dưới đây là chức năng cụ thể của từng layer.

		- __Model__: Layer này sẽ chứa các lớp liên quan đến lưu trữ dữ liệu, hay đảm nhiệm xử lý các nghiệp vụ logic của ứng dụng. Bạn tưởng tượng model giống như bộ não của con người, nó giúp xử lý và lưu trữ dữ liệu.
		- __View__: layer này sẽ chứa các lớp liên quan đến hiển thị, và nhận tương tác từ phía người dùng. Nếu tưởng tượng đến các cơ quan của con người, thì view chính là các giác quan, giúp “nghe”, “ngửi”, “nếm”, “nhìn”, “cảm giác” từ bên ngoài rồi chuyển vào cho model xử lý. Đồng thời có thể “nói” ra môi trường bên ngoài sau khi nhận kết quả xử lý từ model. Thực sự thì view nên trung lập nhất có thể, nó chỉ có thể nhận dữ liệu vào, và hiển thị dữ liệu ra, nó không có “cảm xúc” hay xử lý logic gì cả. Tất cả những gì nó cần làm là lấy dữ liệu từ người dùng rồi gọi đến controller hoặc model để thực hiện tiếp các tác vụ. Rồi sau đó view cũng sẽ hiển thị kết quả sau khi nhận được xử lý từ controller.
		- __Controller__: Layer này chứa các lớp đảm nhận vai trò là cầu nối giữa view và model. Những tương tác của người dùng từ view sẽ được controller chuyển đến model. Và ngược lại, những thay đổi từ model sẽ được controller cập nhật lên view. Như vậy controller giống như các liên kết giúp dẫn truyền “cảm giác” đến não và ngược lại.

- ta sẽ thực hiện thiết kế 1 chương trình có giao diện như sau theo mô hình kiến trúc MVC

<img src="https://github.com/hienqp/Ngay047-ArchitecturePattern-MVCTutorial/blob/main/UI_SAMPLE.png">

__XÂY DỰNG VIEW__