package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.File;


public class Profile {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;

    private File avatar;

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
