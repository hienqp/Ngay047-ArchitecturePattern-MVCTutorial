# __MVC (Model - View - Controller)__

- __MVC__ là viết tắt của 3 chữ: Model – View – Controller.

	- 3 chữ này đại diện cho 3 layer. Các lập trình viên sẽ phải hiểu nguyên tắc của từng layer, để mà tạo ra các lớp tương ứng với các layer đó. Lớp nào thuộc layer nào sẽ tuân thủ theo nguyên tắc của layer đó. Việc đặt tên lớp hay package/directory có thể bao hàm cả tên layer để dễ hiểu và dễ quản lý. Dưới đây là chức năng cụ thể của từng layer.

		- __Model__: Layer này sẽ chứa các lớp liên quan đến lưu trữ dữ liệu, hay đảm nhiệm xử lý các nghiệp vụ logic của ứng dụng. Bạn tưởng tượng model giống như bộ não của con người, nó giúp xử lý và lưu trữ dữ liệu.
		- __View__: layer này sẽ chứa các lớp liên quan đến hiển thị, và nhận tương tác từ phía người dùng. Nếu tưởng tượng đến các cơ quan của con người, thì view chính là các giác quan, giúp “nghe”, “ngửi”, “nếm”, “nhìn”, “cảm giác” từ bên ngoài rồi chuyển vào cho model xử lý. Đồng thời có thể “nói” ra môi trường bên ngoài sau khi nhận kết quả xử lý từ model. Thực sự thì view nên trung lập nhất có thể, nó chỉ có thể nhận dữ liệu vào, và hiển thị dữ liệu ra, nó không có “cảm xúc” hay xử lý logic gì cả. Tất cả những gì nó cần làm là lấy dữ liệu từ người dùng rồi gọi đến controller hoặc model để thực hiện tiếp các tác vụ. Rồi sau đó view cũng sẽ hiển thị kết quả sau khi nhận được xử lý từ controller.
		- __Controller__: Layer này chứa các lớp đảm nhận vai trò là cầu nối giữa view và model. Những tương tác của người dùng từ view sẽ được controller chuyển đến model. Và ngược lại, những thay đổi từ model sẽ được controller cập nhật lên view. Như vậy controller giống như các liên kết giúp dẫn truyền “cảm giác” đến não và ngược lại.

- ta sẽ thực hiện thiết kế 1 chương trình có giao diện như sau theo mô hình kiến trúc MVC

<img src="https://github.com/hienqp/Ngay047-ArchitecturePattern-MVCTutorial/blob/main/UI_SAMPLE.png">

- với ứng dụng như hình trên, ta có hoạt động của ứng dụng như sau
	- user nhập email, password, sau đó click Login
	- chương trình sẽ xử lý với dữ liệu user nhập vào
	- hiển thị message Login thành công hay thất bại cho user biết

___

## __XÂY DỰNG MODEL__

- __Model__ chính là Instance của Object có các thuộc tính __email__ và __password__
- __Model__ là nơi chứa data, xử lý data nhận được từ Controller
- ta sẽ xây dựng 1 class Model có tên là User
- __User.java__
```java
public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // kiểm tra chuỗi email có hợp lệ hay không
    public boolean isValidEmail() {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // kiểm tra chuỗi password có hợp lệ hay không
    public boolean isValidPassword() {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}
```

___

## __XÂY DỰNG VIEW__

- với View chính là những thành phần hiển thị cho user nhìn thấy và tương tác, ở đây chính là file giao diện layout __activity_main.xml__
- thiết kế Layout View __activity_main.xml__ như sau

- __activity_main.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical"
    android:padding="16dp"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/login"
        android:textColor="@color/black"
        android:textSize="30sp" />

    <EditText
        android:id="@+id/edt_email"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:autofillHints="@string/email"
        android:hint="@string/email"
        android:inputType="textEmailAddress"
        android:textColor="@color/black"
        android:textSize="20sp" />

    <EditText
        android:id="@+id/edt_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:autofillHints="@string/password"
        android:hint="@string/password"
        android:inputType="numberPassword"
        android:textColor="@color/black"
        android:textSize="20sp" />

    <TextView
        android:id="@+id/tv_message"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:text="@string/successful_message_here"
        android:textSize="18sp"
        android:visibility="gone" />

    <Button
        android:id="@+id/btn_login"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:text="@string/login"
        android:textSize="20sp" />

</LinearLayout>
```

___

## __XÂY DỰNG CONTROLLER__

- Controller là thành phần sẽ lắng nghe các sự kiện user tương tác đến View, sau đó sẽ chuyển những yêu cầu đó đến Model
- sau khi Model xử lý dữ liệu xong sẽ trả kết quả về cho Controller để cập nhật lên View hiển thị cho user
- ở đây ta sẽ sử dụng __MainActivity.java__ làm Controller để lắng nghe sự kiện ở layout View __activity_main.xml__, sau đó gửi dữ liệu đó đến Model __User.java__, và nhận dữ liệu nhận được từ Model, đồng thời Controller cũng có những tác vụ logic của chính nó, sau đó cập nhật kết quả đến View cho người dùng

- __MainActivity.java__
```java
public class MainActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private TextView tvMessage;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnLogin = (Button) findViewById(R.id.btn_login);

        // lắng nghe sự kiện user click vào Button Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            // nếu user click vào Button Login, method clickLogin() sẽ được gọi
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void clickLogin() {
        // 2 biến tạm dùng để lưu giá trị của 2 EditText
        String strEmail = edtEmail.getText().toString();
        String strPassword = edtPassword.getText().toString();

        // khởi tạo object model User
        User user = new User(strEmail, strPassword);

        // gọi đến các method kiểm tra của Model, để Controller có thể cập nhật dữ liệu của View
        // thành phần được cập nhật trên View chính là TextView message đã được ẩn lúc khi xây dựng,
        // lúc này tùy kết quả Model trả về là gì thì tvMessage sẽ hiển thị kết quả tương ứng
        if (user.isValidEmail() && user.isValidPassword()) {
            tvMessage.setText("Login Success");
            tvMessage.setTextColor(getResources().getColor(R.color.purple_200, null));
        } else {
            tvMessage.setText("Email or Password Invalid");
            tvMessage.setTextColor(getResources().getColor(R.color.purple_700, null));
        }
        tvMessage.setVisibility(View.VISIBLE);
    }
}
```
