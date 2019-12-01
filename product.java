package pos;

class product {
	private String barcode,name,price,stock,category;
	public product(String barcode,String name,String price,String stock,String category) {
		this.barcode=barcode;
		this.name=name;
		this.price=price;
		this.stock=stock;
		this.category=category;
	}
	public String getBarcode() {
		return this.barcode;
	}
	public String getName() {
		return this.name;
	}
	public String getPrice() {
		return this.price;
	}
	public String getStock() {
		return this.stock;
	}
	public String getCategory() {
		return this.category;
	}
	public void setBarcode(String barcode) {
		this.barcode=barcode;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public void setStock(String stock) {
		this.stock=stock;
	}
	public void setCategory(String category) {
		this.category=category;
	}
}