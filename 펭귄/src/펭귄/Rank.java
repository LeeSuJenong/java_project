//20155199 이수정 자바 프로젝트입니다(펭귄게임)

package 펭귄;
public class Rank  implements Comparable<Rank> {
	String name;
    int score;
	
	public Rank (String name, int score) {
		this.name = name; this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return  name +"  ("+ score+"점)";
	}
	public int compareTo(Rank r2) {
		return score-r2.score;
	}
	public boolean equals(Object r2) {
		return this.compareTo((Rank)r2)==0;
	}
	
}