package lambda.entity;

public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", Name=" + Name + ", age=" + age + ", slary=" + slary + ", status=" + status + "]";
	}
	private String id;
	private String Name;
	private int age;
	private double slary;
	private Enum status;
	public double getSlary() {
		return slary;
	}
	public void setSlary(double slary) {
		this.slary = slary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public Enum getStatus() {
		return status;
	}
	public void setStatus(Enum status) {
		this.status = status;
	}
	
	public User() {
	}

	public User(String id, String name, int age, double slary, Enum status) {
		super();
		this.id = id;
		Name = name;
		this.age = age;
		this.slary = slary;
		this.status = status;
	}
	public User(String id, String name, int age, double slary) {
		super();
		this.id = id;
		Name = name;
		this.age = age;
		this.slary = slary;
	}
}
