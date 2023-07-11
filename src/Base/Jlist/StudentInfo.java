package Base.Jlist;

/**
 * 学生实体类
 * 
 * @author ZW
 *
 */
public class StudentInfo {
	private int id;
	private String name;
	private String sex;
	private String phoneNum;

	public StudentInfo() {
		super();
	}

	public StudentInfo(int id, String name, String sex, String phoneNum) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phoneNum = phoneNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", name=" + name + ", sex=" + sex + ", phoneNum=" + phoneNum + "]";
	}
}
