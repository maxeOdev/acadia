package com.hb.acadia.utils;

/**
 * 
 * @author anis
 *
 *         Acadia's domain consts for admin's application
 *
 */
public class DomainConst {

	public final static String CATEGORIES = "Catégories";

	public final static String FORMATEURS = "Formateurs";

	public final static String FORMATIONS = "Formations";

	public final static String UTILISATEURS = "Utilisateurs";

	public final static String VIDEOS = "Vidéos";

	public static String[] getValues() {
		String[] values = new String[5];
		values[0] = CATEGORIES;
		values[1] = FORMATEURS;
		values[2] = FORMATIONS;
		values[3] = UTILISATEURS;
		values[4] = VIDEOS;
		return values;
	}

}
