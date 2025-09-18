import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcontroler {
    public static void connect() {
        String url = "jdbc:sqlite:D:/iainfdatabase.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addtodb(String groundjson, String inventoryjson, int lvl, int hp, int mp){
        String url = "jdbc:sqlite:D:/iainfdatabase.db";
        String sql = "INSERT INTO ground (groundjson, inventoryjson, lvl, hp, mp) VALUES(?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, groundjson);
            pstmt.setString(2, inventoryjson);
            pstmt.setInt(3, lvl);
            pstmt.setInt(4, hp);
            pstmt.setInt(5, mp);

            pstmt.executeUpdate();
            System.out.println("added");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void getgroundjson (){
        String url = "jdbc:sqlite:D:/iainfdatabase.db";
        String sql = "SELECT groundjson FROM ground";

        try (Connection conn = DriverManager.getConnection(url);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
           System.out.print(
                    rs.getString("groundjson")

            );
           Game.board.groundjson= new StringBuilder(rs.getString("groundjson"));
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
}
}
