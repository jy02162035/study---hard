package lambda.entity;

import java.util.Optional;

public class NewMan {
	private Optional<Godness> godness = Optional.empty();

	public Optional<Godness> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	public NewMan() {}

	@Override
	public String toString() {
		return "NewMan [godness=" + godness + "]";
	}
	
	
	
	
}	
