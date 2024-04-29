package jpt;

public class Animal {
	
	public static void main(String[] args) {
		String name = "Arcs";
		float weight = 8.5565f;
		String nameSlave = "SKKU";
		Cat cat = new Cat(name,weight,nameSlave);
		cat.getName();
		cat.setName("Arcs2");
		cat.getName();
		cat.getWeight();
		cat.setWeight(8.556f);
		cat.getWeight();
		cat.getNameSlave();
		cat.setNameSlave("SNU");
		cat.getNameSlave();
		cat.bark();
		cat.getFood();
	}
}
abstract class Mammal extends Animal{
	abstract String bark();
	abstract String getFood();
}
abstract class Reptile extends Animal{
	abstract String getFood();
}
final class Cat extends Mammal{
	private String name;
	private float weight;
	String nameSlave;
	Cat(String name, float weight, String nameSlave){
		this.name = name;
		this.weight = weight;
		this.nameSlave = nameSlave;
	}
	public String bark() {
		System.out.println("Meow");
		return "Meow";
	}
	public String getFood() {
		System.out.println("Fish");
		return "Fish";
	}
	public String getName() {
		System.out.println("Name: "+this.name);
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		System.out.println("Name: "+this.weight);
		return this.weight;
	}
	public void setWeight(float weight) {
		
	}
	public String getNameSlave() {
		System.out.println("Name: "+this.nameSlave);
		return this.nameSlave;
	}
	public void setNameSlave(String name) {
		
	}
}
final class Dog extends Mammal{
	private String name;
	private float weight;
	String nameMaster;
	Dog(String name, float weight, String nameMaster){
		this.name = name;
		this.weight = weight;
		this.nameMaster = nameMaster;
	}
	public String bark() {
		System.out.println("Bowbow");
		return "Bowbow";
	}
	public String getFood() {
		System.out.println("Apple");
		return "Apple";
	}
	public String getName() {
		System.out.println("Name: "+this.name);
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		System.out.println("Name: "+this.weight);
		return this.weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getNameMaster() {
		System.out.println("Name: "+this.nameMaster);
		return this.nameMaster;
	}
	public void setNameMaster(String name) {
		this.name = name;
	}
}
final class Crocodile extends Reptile{
	private String name;
	private float weight;
	public String getFood() {
		System.out.println("Meat");
		return "Meat";
	}
	public String getName() {
		System.out.println("Name: "+this.name);
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		System.out.println("Name: "+this.weight);
		return this.weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
}
