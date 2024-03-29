package service;

import java.io.*;
import java.util.*;

import model.Course;
import model.Student;
import view.MessageView;

public class MyFileReader {

	private File file;
	ArrayList<Student> students = new ArrayList<Student>();
	Course course;
	Object firstLine;

	private static HashMap<Student, ArrayList<Course>> map = new HashMap<>();
	private static ArrayList<String> list = new ArrayList<>();

	static {
		try {
			int num = 1;
			BufferedReader bf = new BufferedReader(new FileReader("src/scorefile/16计机4班成绩.txt"));
			String message = bf.readLine();
			while (message != null) {
				if (num == 1) {
					String [] course = message.split(",");
					list.addAll(Arrays.asList(course));
					num++;
					continue;
				}
				String s [] = message.split("#");
				if (s.length == 2) {

					String stu [] = s[0].split(",");
					Student student = new Student(stu[0],stu[1],
							Integer.parseInt(stu[2]),0);
					String [] cou = s[1].split(",");
					//name=0,  f=0  ,
					ArrayList<Course> list = new ArrayList<>();
					int total = 0;
					for (String c:cou) {
						String [] course = c.split("=");
						Course course1 = new Course(course[0], Integer.parseInt(course[1]),course[2],
								Integer.parseInt(course[3]),Integer.parseInt(course[4]));
						list.add(course1);
						total += course1.getScore();
					}
					student.setFinalScore(total);
					map.put(student,list);
					num++;
				}
				message = bf.readLine();
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Student,ArrayList<Course>> show () {
		return map;
	}

	public static ArrayList<String> getList(){
		return list;
	}



	//====================================================================

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	@SuppressWarnings("unchecked")  
	/*读取学生信息*/
	public ArrayList<Student> getFileStudents() throws NumberFormatException {
		
		if (file.getName().toLowerCase().endsWith(".txt")) {
			try {
				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "GBK"));
				String line = null;
				if((line = br.readLine()) != null) {
					while ((line = br.readLine()) != null) {
						String[]obj = line.split(",");/*以“，”分割*/
						if (obj.length == 5) {
							Student student = new Student(obj[0], obj[1],
									Integer.parseInt(obj[2]),Integer.parseInt(obj[3]));
							students.add(student);
						}
				}
				
				}
				br.close();
			} catch (FileNotFoundException e) {
				MessageView.createView("找不到文件!");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				MessageView.createView("不支持GBK编码错误");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				MessageView.createView("txt文件内容格式错误");
				e.printStackTrace();
			}
			return students;
		} else if (file.getName().toLowerCase().endsWith(".dat")) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				if((firstLine= ois.readObject()) instanceof Course){
					course = (Course)firstLine;
				};
				students = (ArrayList<Student>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				MessageView.createView("找不到文件!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return students;
		} else {
			MessageView.createView("文件后缀名错误！");
		}
		return null;
	}
}
