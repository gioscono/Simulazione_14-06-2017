package it.polito.tdp.artsmia.model;

import java.util.List;

public class Exhibition {
	
	private int exhibitionId;
	private String exhibitionDepartment;
	private String exhibitionTitle;
	private int begin;
	private int end;
	private List<ArtObject> oggetti;
	
	public Exhibition(int exhibitionId, String exhibitionDepartment, String exhibitionTitle, int begin, int end) {
		super();
		this.exhibitionId = exhibitionId;
		this.exhibitionDepartment = exhibitionDepartment;
		this.exhibitionTitle = exhibitionTitle;
		this.begin = begin;
		this.end = end;
	}
	public int getExhibitionId() {
		return exhibitionId;
	}
	public String getExhibitionDepartment() {
		return exhibitionDepartment;
	}
	public String getExhibitionTitle() {
		return exhibitionTitle;
	}
	public int getBegin() {
		return begin;
	}
	public int getEnd() {
		return end;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exhibitionId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exhibition other = (Exhibition) obj;
		if (exhibitionId != other.exhibitionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.exhibitionId+": "+this.exhibitionTitle;
	}
	public List<ArtObject> getOggetti() {
		return oggetti;
	}
	public void setOggetti(List<ArtObject> oggetti) {
		this.oggetti = oggetti;
	}
	
	
	

}
