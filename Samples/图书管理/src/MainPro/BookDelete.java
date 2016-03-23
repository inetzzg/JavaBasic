// BookDelete.java
package MainPro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

import PublicModule.*;

public class BookDelete extends Frame {
	private static final long serialVersionUID = 7451605980497182697L;
	Label lbbookid_1 = new Label("图书编号");
	Label lbbookid = new Label("图书编号");
	Label lbbookname = new Label("图书名称");
	Label lbbooktype = new Label("图书类别");
	Label lbauthor = new Label("作者");
	Label lbtranslator = new Label("译者");
	Label lbpublisher = new Label("出版社");
	Label lbpublish_time = new Label("出版时间");
	Label lbprice = new Label("定价");
	Label lbstock = new Label("库存数量");
	Button saveBtn = new Button("删除");
	Button closeBtn = new Button("关闭");
	Button queryBtn = new Button("查询");
	TextField tf_bookid = new TextField();
	TextField tf_bookname = new TextField();
	TextField tf_author = new TextField();
	TextField tf_translator = new TextField();
	TextField tf_publisher = new TextField();
	TextField tf_publish_time = new TextField();
	TextField tf_price = new TextField();
	TextField tf_stock = new TextField();
	TextField tf_bookid1 = new TextField();
	Choice tf_booktype = new Choice();

	public BookDelete() {
		setLayout(null);
		setTitle("删除图书");
		setSize(500, 280);
		lbbookid_1.setBounds(100, 40, 50, 20);// 图书编号
		tf_bookid1.setBounds(160, 40, 100, 20);
		queryBtn.setBounds(280, 40, 80, 20); // 查询按钮
		queryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_queryActionPerformed(e);
			}
		});
		lbbookid.setBounds(50, 80, 50, 20); // 图书编号
		tf_bookid.setBounds(110, 80, 100, 20);
		lbbookname.setBounds(240, 80, 50, 20); // 图书名称
		tf_bookname.setBounds(300, 80, 100, 20);
		lbbooktype.setBounds(50, 110, 50, 20); // 图书类别
		tf_booktype.setBounds(110, 110, 100, 20);
		tf_booktype.add("科技");
		tf_booktype.add("文学");
		tf_booktype.add("社科");
		tf_booktype.add("其他");
		lbauthor.setBounds(240, 110, 50, 20); // 作者
		tf_author.setBounds(300, 110, 100, 20);
		lbtranslator.setBounds(50, 140, 50, 20); // 译者
		tf_translator.setBounds(110, 140, 100, 20);
		lbpublisher.setBounds(240, 140, 50, 20); // 出版社
		tf_publisher.setBounds(300, 140, 100, 20);
		lbpublish_time.setBounds(50, 170, 50, 20); // 出版时间
		tf_publish_time.setBounds(110, 170, 100, 20);
		lbprice.setBounds(240, 170, 50, 20); // 价格
		tf_price.setBounds(300, 170, 100, 20);
		lbstock.setBounds(50, 200, 50, 20);// 库存数量
		tf_stock.setBounds(110, 200, 100, 20);

		saveBtn.setBounds(150, 240, 80, 25); // 删除按钮
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_delActionPerformed(e);

			}
		});
		closeBtn.setBounds(280, 240, 80, 25); // 关闭按钮
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 释放当前窗体
			}
		});

		/* 关闭窗口 */
		this.addWindowListener(new WindowAdapter() {
			// 重写windowClosing()方法
			public void windowClosing(WindowEvent e) {
				dispose(); // 释放当前窗体
			}
		});
		add(lbbookid); // 将各组件增加到窗体中
		add(lbbookid_1);
		add(lbbookname);
		add(lbbooktype);
		add(lbauthor);
		add(lbtranslator);
		add(lbpublisher);
		add(lbpublish_time);
		add(lbprice);
		add(lbstock);
		add(saveBtn);
		add(closeBtn);
		add(queryBtn);
		add(tf_bookid);
		add(tf_bookname);
		add(tf_author);
		add(tf_translator);
		add(tf_publisher);
		add(tf_publish_time);
		add(tf_price);
		add(tf_stock);
		add(tf_bookid1);
		add(tf_booktype);
		setLocationRelativeTo(null); // 使窗体在屏幕上居中放置
		setVisible(true); // 使窗体可见
	}

	private void btn_delActionPerformed(ActionEvent e) {
		String id = tf_bookid.getText();
		// 如果图书编号为空，则删除操作终止
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		String sql = "delete from book where id='" + id + "'";
		int i = DbOp.executeUpdate(sql);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "图书信息删除成功!");
			// 清空全部文本框
			clearAllTextfield();
		} else
			JOptionPane.showMessageDialog(null, "图书信息删除失败！");
	}

	private void btn_queryActionPerformed(ActionEvent e) {
		String id = tf_bookid1.getText();
		// 如果图书编号为空，则查询操作终止
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}
		// 按编号查询图书，结果存入book对象中
		Book book = BookSelect.SelectBookById(id);
		// 如果查询到结果，将其显示在各文本框中
		if (book != null) {
			tf_bookid.setText(book.getId());
			tf_bookid.setEditable(false);
			tf_bookname.setText(book.getBookname());
			// 将Choice的选定项设置为其名称等于指定字符串的项
			tf_booktype.select(book.getBooktype());
			tf_author.setText(book.getAuthor());
			tf_translator.setText(book.getTranslator());
			tf_publisher.setText(book.getPublisher());
			tf_publish_time.setText(book.getPublish_time().toString());
			tf_price.setText(String.valueOf((book.getPrice())));
			tf_stock.setText(String.valueOf(book.getStock()));
		} else
			JOptionPane.showMessageDialog(null, "图书编号有误，查无此书！");
	}

	// 清空全部文本框
	private void clearAllTextfield() {
		tf_bookid1.setText("");
		tf_bookid.setText("");
		tf_bookname.setText("");
		tf_author.setText("");
		tf_translator.setText("");
		tf_publisher.setText("");
		tf_publish_time.setText("");
		tf_price.setText("");
		tf_stock.setText("");
	}

	// 增加main()方法，主要为了调试程序界面
	public static void main(String[] args) {
		new BookDelete();
	}
}
