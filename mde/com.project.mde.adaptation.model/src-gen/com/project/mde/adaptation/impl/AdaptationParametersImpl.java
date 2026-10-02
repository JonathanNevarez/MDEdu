/**
 */
package com.project.mde.adaptation.impl;

import com.project.mde.adaptation.AdaptationPackage;
import com.project.mde.adaptation.AdaptationParameters;
import com.project.mde.adaptation.FeedbackStyle;
import com.project.mde.adaptation.HintLevel;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationParametersImpl#getHintLevel <em>Hint Level</em>}</li>
 *   <li>{@link com.project.mde.adaptation.impl.AdaptationParametersImpl#getFeedbackStyle <em>Feedback Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationParametersImpl extends MinimalEObjectImpl.Container implements AdaptationParameters {
	/**
	 * The default value of the '{@link #getHintLevel() <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintLevel()
	 * @generated
	 * @ordered
	 */
	protected static final HintLevel HINT_LEVEL_EDEFAULT = HintLevel.CONCEPTUAL;

	/**
	 * The cached value of the '{@link #getHintLevel() <em>Hint Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintLevel()
	 * @generated
	 * @ordered
	 */
	protected HintLevel hintLevel = HINT_LEVEL_EDEFAULT;

	/**
	 * This is true if the Hint Level attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hintLevelESet;

	/**
	 * The default value of the '{@link #getFeedbackStyle() <em>Feedback Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackStyle()
	 * @generated
	 * @ordered
	 */
	protected static final FeedbackStyle FEEDBACK_STYLE_EDEFAULT = FeedbackStyle.CONCISE;

	/**
	 * The cached value of the '{@link #getFeedbackStyle() <em>Feedback Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackStyle()
	 * @generated
	 * @ordered
	 */
	protected FeedbackStyle feedbackStyle = FEEDBACK_STYLE_EDEFAULT;

	/**
	 * This is true if the Feedback Style attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean feedbackStyleESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationParametersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.ADAPTATION_PARAMETERS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HintLevel getHintLevel() {
		return hintLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHintLevel(HintLevel newHintLevel) {
		HintLevel oldHintLevel = hintLevel;
		hintLevel = newHintLevel == null ? HINT_LEVEL_EDEFAULT : newHintLevel;
		boolean oldHintLevelESet = hintLevelESet;
		hintLevelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL, oldHintLevel, hintLevel, !oldHintLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetHintLevel() {
		HintLevel oldHintLevel = hintLevel;
		boolean oldHintLevelESet = hintLevelESet;
		hintLevel = HINT_LEVEL_EDEFAULT;
		hintLevelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL, oldHintLevel, HINT_LEVEL_EDEFAULT, oldHintLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetHintLevel() {
		return hintLevelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeedbackStyle getFeedbackStyle() {
		return feedbackStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeedbackStyle(FeedbackStyle newFeedbackStyle) {
		FeedbackStyle oldFeedbackStyle = feedbackStyle;
		feedbackStyle = newFeedbackStyle == null ? FEEDBACK_STYLE_EDEFAULT : newFeedbackStyle;
		boolean oldFeedbackStyleESet = feedbackStyleESet;
		feedbackStyleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE, oldFeedbackStyle, feedbackStyle, !oldFeedbackStyleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetFeedbackStyle() {
		FeedbackStyle oldFeedbackStyle = feedbackStyle;
		boolean oldFeedbackStyleESet = feedbackStyleESet;
		feedbackStyle = FEEDBACK_STYLE_EDEFAULT;
		feedbackStyleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE, oldFeedbackStyle, FEEDBACK_STYLE_EDEFAULT, oldFeedbackStyleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetFeedbackStyle() {
		return feedbackStyleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL:
				return getHintLevel();
			case AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE:
				return getFeedbackStyle();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL:
				setHintLevel((HintLevel)newValue);
				return;
			case AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE:
				setFeedbackStyle((FeedbackStyle)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL:
				unsetHintLevel();
				return;
			case AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE:
				unsetFeedbackStyle();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_PARAMETERS__HINT_LEVEL:
				return isSetHintLevel();
			case AdaptationPackage.ADAPTATION_PARAMETERS__FEEDBACK_STYLE:
				return isSetFeedbackStyle();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (hintLevel: ");
		if (hintLevelESet) result.append(hintLevel); else result.append("<unset>");
		result.append(", feedbackStyle: ");
		if (feedbackStyleESet) result.append(feedbackStyle); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //AdaptationParametersImpl
