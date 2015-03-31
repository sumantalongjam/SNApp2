package in.sn.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import in.sn.com.component.SNAlertDialog;
import in.sn.com.util.AnimationUtil;
import in.sn.com.util.SNGlobal;

/**
 * Created by sumanta on 20/2/15.
 */
public class LoginActivity extends Activity {

    private EditText userNameEditText, passwordEditText;
    private EditText firstNameET, secondNameET, userNameET, passwordET, cPasswordET;
    private LinearLayout backLinear;
    private ScrollView registerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SNGlobal.sharedPref = getSharedPreferences("sn_preferences", Context.MODE_PRIVATE);
        SNGlobal.editor = SNGlobal.sharedPref.edit();
        boolean isLogout = SNGlobal.sharedPref.getBoolean("isLogout", true);
        if(isLogout){
            setContentView(R.layout.layout_login);
            userNameEditText = (EditText) findViewById(R.id.userNameEditText);
            passwordEditText = (EditText) findViewById(R.id.passwordEditText);
            Button loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setOnClickListener(clickListener);
            TextView registerTextView = (TextView) findViewById(R.id.registerTextView);
            registerTextView.setOnClickListener(clickListener);
            registerLayout = (ScrollView) findViewById(R.id.registerLayout);
            backLinear = (LinearLayout) findViewById(R.id.backLinear);
            backLinear.setOnClickListener(clickListener);
            firstNameET = (EditText) findViewById(R.id.firstNameET);
            secondNameET = (EditText) findViewById(R.id.secondNameET);
            userNameET = (EditText) findViewById(R.id.userNameET);
            passwordET = (EditText) findViewById(R.id.passwordET);
            cPasswordET = (EditText) findViewById(R.id.cPasswordET);
            Button registerButton = (Button) findViewById(R.id.registerButton);
            registerButton.setOnClickListener(clickListener);
        }
        else {
            login(SNGlobal.sharedPref.getString("userName", ""), SNGlobal.sharedPref.getString("password", ""));
        }
    }
    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.loginButton) {
                if (validateLogin()) {
                    String user_name = userNameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    login(user_name, password);
                }
            }
            else if(v.getId() == R.id.registerTextView) {
               AnimationUtil.showView(LoginActivity.this, registerLayout);
            }
            else if(v.getId() == R.id.backLinear) {
                onBackPressed();
            }
            else if(v.getId() == R.id.registerButton) {
                if(validateRegister()) {
                    String firstName = firstNameET.getText().toString().trim();
                    String secondName = secondNameET.getText().toString().trim();
                    String userName = userNameET.getText().toString().trim();
                    String password = passwordET.getText().toString().trim();
//                    RegistrationEntity entity = new RegistrationEntity(firstName, secondName, userName, password);
//                    register(entity);
                }
            }
        }
    };
    @Override
    public void onBackPressed() {
        if(registerLayout.getVisibility() == View.VISIBLE)
            AnimationUtil.hideView(LoginActivity.this, registerLayout);
        else
            super.onBackPressed();
    }
    private boolean validateLogin(){
        String errorMsg=null;
        try{
            String user_name = userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            if(user_name.length() == 0 ){
                errorMsg = "User name field left blank.";
                throw new IllegalAccessException();
            }else if(password.length() == 0){
                errorMsg = "Password field left blank.";
                throw new IllegalAccessException();
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            new SNAlertDialog(LoginActivity.this).show("Error",errorMsg);
            return false;
        }
    }
    private void login(final String user_name, final String password){
        if(user_name.equals("q")&&password.equals("q")) {
            storeDataInPreferences(user_name, password, false);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            String errorMsg="Invalid User Name Or Password";
            new SNAlertDialog(LoginActivity.this).show("Error",errorMsg);
        }
    }
    private boolean validateRegister(){
        boolean isError = false;
        String errorMsg ="Provide value for ";
        String firstName = firstNameET.getText().toString().trim();
        String secondName = secondNameET.getText().toString().trim();
        String userName = userNameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String cPassword = cPasswordET.getText().toString().trim();
        if(firstName.length() == 0 ){
            errorMsg += "First name,";
            isError = true;
        }
        if(secondName.length() == 0){
            errorMsg += " Second name,";
            isError = true;
        }
        if(userName.length() == 0){
            errorMsg += " user name,";
            isError = true;
        }
        if(password.length() == 0){
            errorMsg += " Password,";
            isError = true;
        }
        if(cPassword.length() == 0){
            errorMsg += " Confirm password,";
            isError = true;
        }
        errorMsg = errorMsg.substring(0, errorMsg.length()-1);
        if(isError) {
            new SNAlertDialog(LoginActivity.this).show("Error",errorMsg);
        }
        else {
            if(!password.equals(cPassword))
                isError = true;
        }
        return !isError;
    }
    /*private void register(RegistrationEntity entity){
        if(RegistrationDataHandler.isValidUserName(LoginActivity.this, entity.getUserName())) {
            RegistrationDataHandler.addRegister(LoginActivity.this, entity);
            storeDataInPreferences(entity.getUserName(), entity.getPassword(), false);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            new SNAlertDialog(LoginActivity.this).show("Error","User name already exist");
        }
    }*/
    private void storeDataInPreferences(String user_name, String password, boolean isLogout){
        SNGlobal.editor.putString("userName", user_name);
        SNGlobal.editor.putString("password", password);
        SNGlobal.editor.putBoolean("isLogout", isLogout);
        SNGlobal.editor.commit();
    }
}
