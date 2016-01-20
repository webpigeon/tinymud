package uk.me.webpigeon.phd.mud.modules.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.me.webpigeon.phd.mud.modules.MudObject;

public class Item extends MudObject {
	
	public final String name;
	public final String[] keywords;
	
	public String description;
	public String shortDesc;
	
	public double weight; //weight in grams
	public Set<String> tags;
	
	private List<Item> contained;
	private Item parent;
	
	public Item(String name, String ... keywords) {
		this.name = name;
		this.keywords = keywords;
		this.tags = new HashSet<String>();
		this.contained = new ArrayList<Item>();
	}
	
	public void addChild(Item item) {
		assert tags.contains(Tags.CONTAINER) : "Tried put something in an non-container";
		contained.add(item);
		
		item.setContained(this);
		weight += item.weight;
	}
	
	public void removeChild(Item item) {
		assert tags.contains(Tags.CONTAINER);
		assert this.equals(item.parent);
		assert item != null;
		
		item.setContained(null);
		weight -= item.weight;
	}
	
	private void setContained(Item item) {
		assert parent == null || item == null;
		this.parent = item;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public boolean matches(String checkName) {
		return name.equalsIgnoreCase(checkName);
	}

	public String toString() {
		return name;
	}
	
	public String getDescription(){
		if (description != null) {
			return String.format("%s: %s", name, description);
		} else {
			return String.format("You see an ordinary %s",name);	
		}
	}

	public Collection<Item> getChildren() {
		return Collections.unmodifiableCollection(contained);
	}
	
}