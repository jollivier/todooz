package fr.todooz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {
	private List<String> tags = new ArrayList<String>();

	
	public void add(String tag) {
		tags.add(tag);
	}

	
	public int size() {
		return tags.size();
	}

	
	public void add(String ... tags){
		if(tags!=null){
			for(int element=0;element<tags.length;element++){
				if((this.tags.contains(tags[element])==false)&&(tags[element]!="")&&(tags[element]!=null))
				this.tags.add(tags[element]);
			}
		}
	}

	public void add(){
	}


	public boolean contains(String element) {
		return tags.contains(element);
	}


	public void top(int limite) {
		if(limite>=0){
			if(tags.size()>limite){
				tags = tags.subList(0, limite);
			}
		}
		else{
			tags.clear();
		}
	}


	public void shuffle() {
		Collections.shuffle(tags);
		
		
	}
}
