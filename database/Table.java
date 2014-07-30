package me.SirSlender.ToAInstances.util.database;

public class Table {
	private String name;
	private String usage;

	public Table(String name, String usage) {
		this.name = name;
		this.usage = usage;
	}
	
	public Table(String name){
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getUsage() {
		return " ("+usage+")";
	}

	public String getValues() {
		String v = "";
		String[] a = usage.split(",");
		int i = 0;
		for(String b : a) {
			i += 1;
			String[] c = b.split(" ");
			v += c[0] == null ? "" : c[0] + (i <= (a.length -1) ? "," : "");
		}

		return "("+v+")";
	}
}