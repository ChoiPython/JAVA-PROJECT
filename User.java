import javax.swing.JTextField;

public class User {
	private int id;
	private String name;
	private String depart;
	private String rank;
	private int halfway;
	private int reward;
	private int point;
	private String imgaddr;
	
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
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getHalfway() {
		return halfway;
	}
	public void setHalfway(int halfway) {
		this.halfway = halfway;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getImgaddr() {
		return imgaddr;
	}
	public void setImgaddr(String imgaddr) {
		this.imgaddr = imgaddr;
	}

	public void setAll(String name, String depart, String rank,int halfway, int point) {
		this.name = name;
		this.depart = depart;
		this.rank = rank;
		this.halfway=halfway;
		this.point = point;
		this.imgaddr = null; // 수정예정
	}
	
	@Override
	public String toString() {
		return "사번 : "+getId()+" 이름 : "+getName()+" 부서 : "+getDepart()+" 직급 : "+getRank()+ " 포인트 : "+getPoint();
	}
	
}
