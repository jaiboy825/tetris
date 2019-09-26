package net.gondr.views;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.gondr.domain.ScoreVO;
import net.gondr.tetris.App;
import net.gondr.util.JDBCUtil;

public class ScorePopupController {
	@FXML
	private TextField txtName;
	
	@FXML
	private Label lblScore;
	
	@FXML
	private Label lblError;
	
	
	
	private Stage me;
	private int score;
	
	public void setDialogStage(Stage stage) {
		me = stage;
	}
	
	public void setScore(int score) {
		this.score = score;
		lblScore.setText("당신의 점수는  " + score + "입니다.");
		lblError.setText("");
	}
	
	public void record() {
		if(txtName.getText().isEmpty()) {
			lblError.setText("이름은 비어있을 수 없습니다");
			return;
		}
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tetris (name, score) VALUES (?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txtName.getText());
			pstmt.setInt(2, score);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("데이터베이스 쿼리중 오류 발생");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		//gondr.asuscomm.com/phpmyadmin
		
		App.app.game.reloadTopScore();
		
		this.me.close();
	}
}

