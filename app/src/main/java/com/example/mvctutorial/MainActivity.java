package com.example.mvctutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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