// TextEditor.java
package Chapter9;

import java.awt.*;
import java.awt.event.*; // 引入所有的事件类
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;

public class TextEditor extends Frame implements ActionListener {
	MenuBar mainmenubar = new MenuBar(); // 声明菜单条
	Menu file; // 声明主菜单项
	MenuItem nw; // 声明各子菜单项
	MenuItem op;
	MenuItem cl;
	MenuItem sf;
	MenuItem ex;
	TextArea tx; // 声明文本区对象

	public TextEditor(String title) {
		super(title); // 调用父类构造方法
		CloseHandler handler = new CloseHandler(); // 定义窗体事件的侦听器对象
		this.addWindowListener(handler); // 为当前窗体注册侦听器对象
		setSize(400, 400); // 设置窗体尺寸
		setLocationRelativeTo(null); // 使窗体在屏幕上居中放置
		menuinit(); // 构建与处理菜单
		tx = new TextArea(); // 创建文本区对象
		this.add(tx); // 将文本区对象放入窗体
		setVisible(true); // 使窗体可见
	}

	// 菜单构建与处理
	void menuinit() {
		mainmenubar = new MenuBar(); // 定义主菜单栏
		file = new Menu("文件"); // 定义主菜单项file
		nw = new MenuItem("新建文件"); // 定义各子菜单项
		op = new MenuItem("打开文件");
		cl = new MenuItem("关闭文件");
		sf = new MenuItem("保存文件");
		ex = new MenuItem("退          出");
		file.add(nw); // 将各子菜单项加入到主菜单项中
		file.add(op);
		file.add(cl);
		file.add(sf);
		file.add(ex);
		mainmenubar.add(file); // 将主菜单项加入到主菜单栏
		setMenuBar(mainmenubar); // 为窗体设置主菜单
		nw.addActionListener(this); // 为各菜单项注册事件侦听器
		op.addActionListener(this);
		cl.addActionListener(this);
		sf.addActionListener(this);
		ex.addActionListener(this);
	}

	// 窗体的ActionEvent事件处理方法
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource(); // 获取事件对象
		JFileChooser f = new JFileChooser(); // 创建文件选择器对象
		if ((ob == nw) || (ob == cl)) { // 选择"新建文件"或"关闭文件"子菜单项
			tx.setText(""); // 清空文本区
		} else if (ob == op) { // 选择"打开文件"子菜单项
			// 弹出具有自定义 approve 按钮的自定义文件选择器对话框
			f.showOpenDialog(this);
			try {
				// 定义一个字符缓冲器对象s
				StringBuffer s = new StringBuffer();
				// 构造一个FileReadder对象in，其参数为在文件选择器
				// 对话框中选中的文件
				FileReader in = new FileReader(f.getSelectedFile());
				// 读入文件内容，将其放入字符缓冲器对象s中
				while (true) {
					int b = in.read();
					if (b == -1)
						break;
					s.append((char) b);
				}
				tx.setText(s.toString()); // 将文件内容显示在文本区
				in.close(); // 关闭文件
			} catch (Exception ee) {
			}
		} else if (ob == sf) { // 选择"保存文件"子菜单项
			f.showSaveDialog(this); // 显示文件选择对话框
			try {
				// 创建FileWriter对象，其参数为前面选择的文件
				FileWriter out = new FileWriter(f.getSelectedFile());
				out.write(tx.getText()); // 将文本区内容写入文件
				out.close(); // 关闭文件
			} catch (Exception ee) {
			}
		} else if (ob == ex) // 选择"退    出"子菜单项
			System.exit(0); // 退出系统
	}

	public static void main(String[] args) {
		new TextEditor("简易文本编辑器");
	}
}

// CloseHandler类实现关闭窗口的功能
class CloseHandler extends WindowAdapter { // 继承适配器类
	public void windowClosing(WindowEvent e) { // 处理关闭窗口事件的方法
		System.exit(0); // 终止当前线程
	}
}
