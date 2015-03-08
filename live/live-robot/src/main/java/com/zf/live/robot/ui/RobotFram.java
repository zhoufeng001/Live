package com.zf.live.robot.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.zf.live.robot.Params;
import com.zf.live.robot.WebSocketService;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RobotFram extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JTextField jTextField2;
	private JLabel jLabel5;
	private JTextField jTextField5;
	private JLabel jLabel6;
	private JTextField jTextField6;
	private JLabel jLabel7;
	private JCheckBox jCheckBox0;
	private JLabel jLabel8;
	private JTextField jTextField7;
	private JButton jButton0;
	public RobotFram() {
		initComponents();
		addListener();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addListener(){
		
		getJButton0().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok");
				getJButton0().setEnabled(false); 
				Params params = getParams();
				WebSocketService service = new WebSocketService();
				service.joinRoom(params); 
			}
		});
		
	}

	private Params getParams(){
		Params params = new Params() ;
		params.setActionInterval(Long.parseLong(getJTextField7().getText()));
		params.setDoChat(getJCheckBox0().isSelected());
		params.setPassword(getJTextField5().getText());
		params.setUserFrom(Integer.parseInt(getJTextField1().getText()));
		params.setUserTo(Integer.parseInt(getJTextField2().getText()));
		params.setUserPrefix(getJTextField0().getText());
		params.setVideoId(getJTextField6().getText());
		return params ;
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(15, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(72, 102, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(12, 12, 12), new Leading(43, 10, 10)));
		add(getJTextField1(), new Constraints(new Leading(72, 55, 10, 10), new Leading(41, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(151, 10, 10), new Leading(43, 12, 12)));
		add(getJTextField2(), new Constraints(new Leading(217, 56, 10, 10), new Leading(39, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(38, 12, 12), new Leading(67, 12, 12)));
		add(getJTextField5(), new Constraints(new Leading(72, 92, 12, 12), new Leading(67, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(30, 10, 10), new Leading(97, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(14, 12, 12), new Leading(128, 12, 12)));
		add(getJCheckBox0(), new Constraints(new Leading(67, 10, 10), new Leading(127, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(112, 12, 12), new Leading(128, 12, 12)));
		add(getJTextField7(), new Constraints(new Leading(169, 58, 10, 10), new Leading(126, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(16, 10, 10), new Leading(166, 10, 10)));
		add(getJTextField6(), new Constraints(new Leading(72, 271, 10, 10), new Leading(97, 12, 12)));
		setSize(441, 263);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("开始");
		}
		return jButton0;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setText("3000");
		}
		return jTextField7;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("聊天间隔");
		}
		return jLabel8;
	}

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setSelected(true);
		}
		return jCheckBox0;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("是否聊天");
		}
		return jLabel7;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText("6699158fad0611e4ba363417ebbccb7e016");
		}
		return jTextField6;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("视频Id");
		}
		return jLabel6;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setText("111111");
		}
		return jTextField5;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("密码");
		}
		return jLabel5;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText("100");
		}
		return jTextField2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("结束用户");
		}
		return jLabel2;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("1");
		}
		return jTextField1;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("开始用户");
		}
		return jLabel1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("zzzz");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("用户前缀");
		}
		return jLabel0;
	}

}
