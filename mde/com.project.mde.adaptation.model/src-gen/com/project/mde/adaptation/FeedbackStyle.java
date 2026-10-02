/**
 */
package com.project.mde.adaptation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Feedback Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.adaptation.AdaptationPackage#getFeedbackStyle()
 * @model
 * @generated
 */
public enum FeedbackStyle implements Enumerator {
	/**
	 * The '<em><b>CONCISE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCISE_VALUE
	 * @generated
	 * @ordered
	 */
	CONCISE(0, "CONCISE", "CONCISE"),

	/**
	 * The '<em><b>EXPLANATORY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPLANATORY_VALUE
	 * @generated
	 * @ordered
	 */
	EXPLANATORY(1, "EXPLANATORY", "EXPLANATORY");

	/**
	 * The '<em><b>CONCISE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCISE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONCISE_VALUE = 0;

	/**
	 * The '<em><b>EXPLANATORY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPLANATORY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXPLANATORY_VALUE = 1;

	/**
	 * An array of all the '<em><b>Feedback Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final FeedbackStyle[] VALUES_ARRAY =
		new FeedbackStyle[] {
			CONCISE,
			EXPLANATORY,
		};

	/**
	 * A public read-only list of all the '<em><b>Feedback Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<FeedbackStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Feedback Style</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static FeedbackStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FeedbackStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Feedback Style</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static FeedbackStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FeedbackStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Feedback Style</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static FeedbackStyle get(int value) {
		switch (value) {
			case CONCISE_VALUE: return CONCISE;
			case EXPLANATORY_VALUE: return EXPLANATORY;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private FeedbackStyle(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //FeedbackStyle
