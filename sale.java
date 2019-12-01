package pos;

public class sale {
	public sale(String category,int price) {
		this.category=category;
		this.price=price;
	}
String category;
int price;
public void setCategory(String cateogry) {
	this.category = category;
}
public void setPrice(int price) {
	this.price=price;
}
public String getCategory() {
	return this.category;
}
public int getPrice() {
	return this.price;
}
}
