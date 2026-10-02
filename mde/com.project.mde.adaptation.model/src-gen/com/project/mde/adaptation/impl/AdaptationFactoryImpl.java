/**
 */
package com.project.mde.adaptation.impl;

import com.project.mde.adaptation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdaptationFactoryImpl extends EFactoryImpl implements AdaptationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AdaptationFactory init() {
		try {
			AdaptationFactory theAdaptationFactory = (AdaptationFactory)EPackage.Registry.INSTANCE.getEFactory(AdaptationPackage.eNS_URI);
			if (theAdaptationFactory != null) {
				return theAdaptationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AdaptationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AdaptationPackage.ADAPTATION_RULE_SET: return createAdaptationRuleSet();
			case AdaptationPackage.ADAPTATION_RULE: return createAdaptationRule();
			case AdaptationPackage.EVENT: return createEvent();
			case AdaptationPackage.COMPARISON_CONDITION: return createComparisonCondition();
			case AdaptationPackage.LOGICAL_CONDITION: return createLogicalCondition();
			case AdaptationPackage.NOT_CONDITION: return createNotCondition();
			case AdaptationPackage.STRING_VALUE: return createStringValue();
			case AdaptationPackage.INTEGER_VALUE: return createIntegerValue();
			case AdaptationPackage.DECIMAL_VALUE: return createDecimalValue();
			case AdaptationPackage.BOOLEAN_VALUE: return createBooleanValue();
			case AdaptationPackage.ACTION: return createAction();
			case AdaptationPackage.ADAPTATION_PARAMETERS: return createAdaptationParameters();
			case AdaptationPackage.ADAPTATION_DECISION: return createAdaptationDecision();
			case AdaptationPackage.ADAPTATION_EXPLANATION: return createAdaptationExplanation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case AdaptationPackage.EVENT_TYPE:
				return createEventTypeFromString(eDataType, initialValue);
			case AdaptationPackage.COMPARISON_OPERATOR:
				return createComparisonOperatorFromString(eDataType, initialValue);
			case AdaptationPackage.LOGICAL_OPERATOR:
				return createLogicalOperatorFromString(eDataType, initialValue);
			case AdaptationPackage.ACTION_TYPE:
				return createActionTypeFromString(eDataType, initialValue);
			case AdaptationPackage.HINT_LEVEL:
				return createHintLevelFromString(eDataType, initialValue);
			case AdaptationPackage.FEEDBACK_STYLE:
				return createFeedbackStyleFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case AdaptationPackage.EVENT_TYPE:
				return convertEventTypeToString(eDataType, instanceValue);
			case AdaptationPackage.COMPARISON_OPERATOR:
				return convertComparisonOperatorToString(eDataType, instanceValue);
			case AdaptationPackage.LOGICAL_OPERATOR:
				return convertLogicalOperatorToString(eDataType, instanceValue);
			case AdaptationPackage.ACTION_TYPE:
				return convertActionTypeToString(eDataType, instanceValue);
			case AdaptationPackage.HINT_LEVEL:
				return convertHintLevelToString(eDataType, instanceValue);
			case AdaptationPackage.FEEDBACK_STYLE:
				return convertFeedbackStyleToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationRuleSet createAdaptationRuleSet() {
		AdaptationRuleSetImpl adaptationRuleSet = new AdaptationRuleSetImpl();
		return adaptationRuleSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationRule createAdaptationRule() {
		AdaptationRuleImpl adaptationRule = new AdaptationRuleImpl();
		return adaptationRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComparisonCondition createComparisonCondition() {
		ComparisonConditionImpl comparisonCondition = new ComparisonConditionImpl();
		return comparisonCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LogicalCondition createLogicalCondition() {
		LogicalConditionImpl logicalCondition = new LogicalConditionImpl();
		return logicalCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotCondition createNotCondition() {
		NotConditionImpl notCondition = new NotConditionImpl();
		return notCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringValue createStringValue() {
		StringValueImpl stringValue = new StringValueImpl();
		return stringValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerValue createIntegerValue() {
		IntegerValueImpl integerValue = new IntegerValueImpl();
		return integerValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DecimalValue createDecimalValue() {
		DecimalValueImpl decimalValue = new DecimalValueImpl();
		return decimalValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanValue createBooleanValue() {
		BooleanValueImpl booleanValue = new BooleanValueImpl();
		return booleanValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationParameters createAdaptationParameters() {
		AdaptationParametersImpl adaptationParameters = new AdaptationParametersImpl();
		return adaptationParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationDecision createAdaptationDecision() {
		AdaptationDecisionImpl adaptationDecision = new AdaptationDecisionImpl();
		return adaptationDecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationExplanation createAdaptationExplanation() {
		AdaptationExplanationImpl adaptationExplanation = new AdaptationExplanationImpl();
		return adaptationExplanation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventType createEventTypeFromString(EDataType eDataType, String initialValue) {
		EventType result = EventType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEventTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComparisonOperator createComparisonOperatorFromString(EDataType eDataType, String initialValue) {
		ComparisonOperator result = ComparisonOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalOperator createLogicalOperatorFromString(EDataType eDataType, String initialValue) {
		LogicalOperator result = LogicalOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLogicalOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType createActionTypeFromString(EDataType eDataType, String initialValue) {
		ActionType result = ActionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HintLevel createHintLevelFromString(EDataType eDataType, String initialValue) {
		HintLevel result = HintLevel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHintLevelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeedbackStyle createFeedbackStyleFromString(EDataType eDataType, String initialValue) {
		FeedbackStyle result = FeedbackStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFeedbackStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationPackage getAdaptationPackage() {
		return (AdaptationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AdaptationPackage getPackage() {
		return AdaptationPackage.eINSTANCE;
	}

} //AdaptationFactoryImpl
