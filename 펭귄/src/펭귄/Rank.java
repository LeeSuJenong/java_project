//20155199 �̼��� �ڹ� ������Ʈ�Դϴ�(��ϰ���)

package ���;
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
		return  name +"  ("+ score+"��)";
	}
	public int compareTo(Rank r2) {
		return score-r2.score;
	}
	public boolean equals(Object r2) {
		return this.compareTo((Rank)r2)==0;
	}
	
}