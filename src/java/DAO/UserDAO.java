/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author admin
 */
import DTO.User;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserDAO {
public List<User> selectAll(int offset, int limit) throws SQLException, ClassNotFoundException {
    List<User> users = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM account ORDER BY id_account OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, limit);
        }
        
        rs = stm.executeQuery();
        
        while (rs.next()) {
            String idAccount = rs.getString("id_account");
            String avatar = rs.getString("avatar");
            String accountName = rs.getString("accountname");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            String phoneNumber = rs.getString("phonenumber");
            Date yob = rs.getDate("yearofbirth");
            String email = rs.getString("email");
            boolean receiveEmail = rs.getBoolean("receive_email");
            String password = rs.getString("password");
            String verificationCode = rs.getString("verificationcode");
            Date effectiveTime = rs.getDate("effectivetime");
            boolean authentication = rs.getBoolean("authentication");
            boolean isAdmin = rs.getBoolean("isAdmin");

            // Ensure the User constructor matches these parameters
            User user = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email, receiveEmail, password, verificationCode, effectiveTime, authentication, isAdmin);
            users.add(user);
        }
         return users;
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
   
}

public int countAllUsers() throws SQLException, ClassNotFoundException {
       int count = 0;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT COUNT(*) AS total FROM account";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return count;
    }
    public User selectById(User t) throws SQLException, ClassNotFoundException {
        User result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE id_account=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, t.getIdAccount());
                System.out.println(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idAccount = rs.getString("id_account");
                    String avatar = rs.getString("avatar");
                    String accountName = rs.getString("accountname");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phonenumber");
                    Date yob = rs.getDate("yearofbirth");
                    String email = rs.getString("email");
                    boolean receiveEmail = rs.getBoolean("receive_email");
                    String password = rs.getString("password");
                    String verificationCode = rs.getString("verificationcode");
                    Date efffectiveTime = rs.getDate("effectivetime");
                    boolean authentication = rs.getBoolean("authentication");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email, receiveEmail, password, verificationCode, efffectiveTime, authentication, isAdmin);

                }
            }

        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;

    }

    public User selectByUsernameAndPassWord(User t, boolean isAdmin) throws SQLException, ClassNotFoundException {
        User result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE accountname =? AND password =? AND isAdmin =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, t.getAccountName());
                stm.setString(2, t.getPassword());
                stm.setBoolean(3, isAdmin);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String idAccount = rs.getString("id_account");
                    String avatar = rs.getString("avatar");
                    String accountName = rs.getString("accountname");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phonenumber");
                    Date yob = rs.getDate("yearofbirth");
                    String email = rs.getString("email");
                    boolean receiveEmail = rs.getBoolean("receive_email");
                    String password = rs.getString("password");
                    String verificationCode = rs.getString("verificationcode");
                    Date effectiveTime = rs.getDate("effectivetime");
                    boolean authentication = rs.getBoolean("authentication");
                    boolean isAdminResult = rs.getBoolean("isAdmin");

                    result = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email, receiveEmail, password, verificationCode, effectiveTime, authentication, isAdminResult);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int insert(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO account (id_account, avatar, accountname, firstname, lastname, gender, address, phonenumber, yearofbirth, email, receive_email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, t.getIdAccount());
                stm.setString(2, t.getAvatar());
                stm.setString(3, t.getAccountName());
                stm.setString(4, t.getFirstname());
                stm.setString(5, t.getLastname());
                stm.setString(6, t.getGender());
                stm.setString(7, t.getAddress());
                stm.setString(8, t.getPhoneNumber());
                stm.setDate(9, t.getYob());
                stm.setString(10, t.getEmail());
                stm.setBoolean(11, t.isReceiveEmail());
                stm.setString(12, t.getPassword());

                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public int delete(String idUser) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {

            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "DELETE FROM account WHERE id_account = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, idUser);
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public int update(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE account " + " SET " + " avatar=?" + " accountname=?" + ", firstname=?" + ", lastname=?" + ", gender=?"
                        + ", address=?" + ", phonenumber=?" + ", yearofbirth=?" + ", email=?" + ", receive_email=?"
                        + ", password=?" + " WHERE id_account=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, t.getAvatar());
                stm.setString(1, t.getAccountName());
                stm.setString(2, t.getFirstname());
                stm.setString(3, t.getLastname());
                stm.setString(4, t.getGender());
                stm.setString(5, t.getAddress());
                stm.setString(6, t.getPhoneNumber());
                stm.setDate(7, t.getYob());
                stm.setString(8, t.getEmail());
                stm.setBoolean(11, t.isReceiveEmail());
                stm.setString(12, t.getPassword());
                stm.setString(13, t.getIdAccount());

                System.out.println(sql);
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public int updateVerifyInformation(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            // Bước 1: tạo kết nối đến CSDL
            con = DBUtil.getConnection();

            if (con != null) {
                String sql = "UPDATE account " + " SET " + " verificationcode=?" + ", effectivetime=?" + ", authentication=?" + " WHERE id_account=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, t.getVerificationCode());
                stm.setDate(2, t.getEffectiveTime());
                stm.setBoolean(3, t.isAuthentication());
                stm.setString(4, t.getIdAccount());

                System.out.println(sql);
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public int updateInfo(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {

            con = DBUtil.getConnection();
            String sql = "UPDATE account " + " SET " + " accountname=?" + ", firstname=?"
                    + ", lastname=?" + ", gender=?" + ", address=?" + ", phonenumber=?" + ", yearofbirth=?"
                    + ", email=?" + ", receive_email=?" + " WHERE id_account=?";

            stm = con.prepareStatement(sql);
            stm.setString(1, t.getAccountName());
            stm.setString(2, t.getFirstname());
            stm.setString(3, t.getLastname());
            stm.setString(4, t.getGender());
            stm.setString(5, t.getAddress());
            stm.setString(6, t.getPhoneNumber());
            stm.setDate(7, t.getYob());
            stm.setString(8, t.getEmail());
            stm.setBoolean(9, t.isReceiveEmail());
            stm.setString(10, t.getIdAccount());

            System.out.println(sql);
            result = stm.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + result + " dòng bị thay đổi!");

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean changePassword(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {

            con = DBUtil.getConnection();
            if (con != null) {
                // Bước 2: tạo ra đối tượng statement
                String sql = "UPDATE account " + " SET " + " password=?" + " WHERE id_account=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, t.getPassword());
                stm.setString(2, t.getIdAccount());

                System.out.println(sql);
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");
                return result > 0;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean kiemTraTenDangNhap(String tenDangNhap) throws SQLException, ClassNotFoundException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {

            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE accountname=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, tenDangNhap);

                System.out.println(sql);
                rs = stm.executeQuery();

                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public User selectByUserId(String userId) throws SQLException, ClassNotFoundException {
        User result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE id_account=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                System.out.println(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idAccount = rs.getString("id_account");
                    String avatar = rs.getString("avatar");
                    String accountName = rs.getString("accountname");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phonenumber");
                    Date yob = rs.getDate("yearofbirth");
                    String email = rs.getString("email");
                    boolean receiveEmail = rs.getBoolean("receive_email");
                    String password = rs.getString("password");
                    String verificationCode = rs.getString("verificationcode");
                    Date efffectiveTime = rs.getDate("effectivetime");
                    boolean authentication = rs.getBoolean("authentication");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email, receiveEmail, password, verificationCode, efffectiveTime, authentication, isAdmin);
                }
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public List<User> searchInformation(String searchValue) throws SQLException, ClassNotFoundException {
        List<User> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE accountname LIKE ? OR address LIKE ? OR phonenumber LIKE ? OR email LIKE ?";
                stm = con.prepareStatement(sql);
                String searchPattern = "%" + searchValue + "%";
                stm.setString(1, searchPattern);
                stm.setString(2, searchPattern);
                stm.setString(3, searchPattern);
                stm.setString(4, searchPattern);
                System.out.println(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idAccount = rs.getString("id_account");
                    String avatar = rs.getString("avatar");
                    String accountName = rs.getString("accountname");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phonenumber");
                    Date yob = rs.getDate("yearofbirth");
                    String email = rs.getString("email");
                    boolean receiveEmail = rs.getBoolean("receive_email");
                    String password = rs.getString("password");
                    String verificationCode = rs.getString("verificationcode");
                    Date efffectiveTime = rs.getDate("effectivetime");
                    boolean authentication = rs.getBoolean("authentication");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    User result = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email, receiveEmail, password, verificationCode, efffectiveTime, authentication, isAdmin);
                    ketQua.add(result);
                }
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ketQua;
    }

    public List<String> selectByNameTop5(String name) throws SQLException, ClassNotFoundException {
        List<String> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 5 "
                        + "    accountname "
                        + "FROM "
                        + "    account  "
                        + "WHERE "
                        + "    accountname LIKE ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, name + "%");

                rs = stm.executeQuery();
                while (rs.next()) {

                    String accountName = rs.getString("accountname");
                    ketQua.add(accountName);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ketQua;
    }
    public User selectByUserEmail(String email) throws SQLException, ClassNotFoundException {
    User result = null;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM account WHERE email = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();

            if (rs.next()) {
                String idAccount = rs.getString("id_account");
                String avatar = rs.getString("avatar");
                String accountName = rs.getString("accountname");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phonenumber");
                Date yob = rs.getDate("yearofbirth");
                String email1 = rs.getString("email");
                boolean receiveEmail = rs.getBoolean("receive_email");
                String password = rs.getString("password");
                String verificationCode = rs.getString("verificationcode");
                Date effectiveTime = rs.getDate("effectivetime");
                boolean authentication = rs.getBoolean("authentication");
                boolean isAdmin = rs.getBoolean("isAdmin");
                result = new User(idAccount, avatar, accountName, firstname, lastname, gender, address, phoneNumber, yob, email1, receiveEmail, password, verificationCode, effectiveTime, authentication, isAdmin);
            }
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return result;
}
 public int updateVerifyPassword(User t) throws SQLException, ClassNotFoundException {
    int result = 0;
    Connection con = null;
    PreparedStatement stm = null;
    try {
        // Step 1: Create connection to the database
        con = DBUtil.getConnection();

        if (con != null) {
            String sql = "UPDATE account SET verificationcode=?, effectivetime=? WHERE id_account=?";

            stm = con.prepareStatement(sql);
            stm.setString(1, t.getVerificationCode());
            stm.setDate(2, t.getEffectiveTime());
            stm.setString(3, t.getIdAccount());

            System.out.println(stm); // This will print the final prepared statement
            result = stm.executeUpdate();

            System.out.println("Executed: " + sql);
            System.out.println(result + " rows affected!");

        }
    } finally {
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return result;
}
 
 public int updatePassword(User t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            // Bước 1: tạo kết nối đến CSDL
            con = DBUtil.getConnection();

            if (con != null) {
                String sql = "UPDATE account " + " SET " +  " password=?" + " WHERE id_account=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, t.getPassword());
                stm.setString(2, t.getIdAccount());

                System.out.println(sql);
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}
