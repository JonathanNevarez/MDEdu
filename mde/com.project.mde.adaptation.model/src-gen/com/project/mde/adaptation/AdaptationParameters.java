/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.AdaptationParameters#getHintLevel <em>Hint Level</em>}</li>
 *   <li>{@link com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle <em>Feedback Style</em>}</li>
 * </ul>
 *
 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationParameters()
 * @model
 * @generated
 */
public interface AdaptationParameters extends EObject {
	/**
	 * Returns the value of the '<em><b>Hint Level</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.HintLevel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Level</em>' attribute.
	 * @see com.project.mde.adaptation.HintLevel
	 * @see #isSetHintLevel()
	 * @see #unsetHintLevel()
	 * @see #setHintLevel(HintLevel)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationParameters_HintLevel()
	 * @model unsettable="true"
	 * @generated
	 */
	HintLevel getHintLevel();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getHintLevel <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Level</em>' attribute.
	 * @see com.project.mde.adaptation.HintLevel
	 * @see #isSetHintLevel()
	 * @see #unsetHintLevel()
	 * @see #getHintLevel()
	 * @generated
	 */
	void setHintLevel(HintLevel value);

	/**
	 * Unsets the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getHintLevel <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHintLevel()
	 * @see #getHintLevel()
	 * @see #setHintLevel(HintLevel)
	 * @generated
	 */
	void unsetHintLevel();

	/**
	 * Returns whether the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getHintLevel <em>Hint Level</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Hint Level</em>' attribute is set.
	 * @see #unsetHintLevel()
	 * @see #getHintLevel()
	 * @see #setHintLevel(HintLevel)
	 * @generated
	 */
	boolean isSetHintLevel();

	/**
	 * Returns the value of the '<em><b>Feedback Style</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.adaptation.FeedbackStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feedback Style</em>' attribute.
	 * @see com.project.mde.adaptation.FeedbackStyle
	 * @see #isSetFeedbackStyle()
	 * @see #unsetFeedbackStyle()
	 * @see #setFeedbackStyle(FeedbackStyle)
	 * @see com.project.mde.adaptation.AdaptationPackage#getAdaptationParameters_FeedbackStyle()
	 * @model unsettable="true"
	 * @generated
	 */
	FeedbackStyle getFeedbackStyle();

	/**
	 * Sets the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle <em>Feedback Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feedback Style</em>' attribute.
	 * @see com.project.mde.adaptation.FeedbackStyle
	 * @see #isSetFeedbackStyle()
	 * @see #unsetFeedbackStyle()
	 * @see #getFeedbackStyle()
	 * @generated
	 */
	void setFeedbackStyle(FeedbackStyle value);

	/**
	 * Unsets the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle <em>Feedback Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFeedbackStyle()
	 * @see #getFeedbackStyle()
	 * @see #setFeedbackStyle(FeedbackStyle)
	 * @generated
	 */
	void unsetFeedbackStyle();

	/**
	 * Returns whether the value of the '{@link com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle <em>Feedback Style</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Feedback Style</em>' attribute is set.
	 * @see #unsetFeedbackStyle()
	 * @see #getFeedbackStyle()
	 * @see #setFeedbackStyle(FeedbackStyle)
	 * @generated
	 */
	boolean isSetFeedbackStyle();

} // AdaptationParameters
