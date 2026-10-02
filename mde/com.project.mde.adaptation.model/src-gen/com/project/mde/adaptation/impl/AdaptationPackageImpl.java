/**
 */
package com.project.mde.adaptation.impl;

import com.project.mde.adaptation.Action;
import com.project.mde.adaptation.ActionType;
import com.project.mde.adaptation.AdaptationDecision;
import com.project.mde.adaptation.AdaptationExplanation;
import com.project.mde.adaptation.AdaptationFactory;
import com.project.mde.adaptation.AdaptationPackage;
import com.project.mde.adaptation.AdaptationParameters;
import com.project.mde.adaptation.AdaptationRule;
import com.project.mde.adaptation.AdaptationRuleSet;
import com.project.mde.adaptation.BooleanValue;
import com.project.mde.adaptation.ComparisonCondition;
import com.project.mde.adaptation.ComparisonOperator;
import com.project.mde.adaptation.Condition;
import com.project.mde.adaptation.DecimalValue;
import com.project.mde.adaptation.Event;
import com.project.mde.adaptation.EventType;
import com.project.mde.adaptation.FeedbackStyle;
import com.project.mde.adaptation.HintLevel;
import com.project.mde.adaptation.IntegerValue;
import com.project.mde.adaptation.LogicalCondition;
import com.project.mde.adaptation.LogicalOperator;
import com.project.mde.adaptation.NotCondition;
import com.project.mde.adaptation.StringValue;
import com.project.mde.adaptation.Value;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdaptationPackageImpl extends EPackageImpl implements AdaptationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationRuleSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparisonConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationParametersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationDecisionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationExplanationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eventTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum comparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum logicalOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum actionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum hintLevelEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum feedbackStyleEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.project.mde.adaptation.AdaptationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AdaptationPackageImpl() {
		super(eNS_URI, AdaptationFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link AdaptationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AdaptationPackage init() {
		if (isInited) return (AdaptationPackage)EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAdaptationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AdaptationPackageImpl theAdaptationPackage = registeredAdaptationPackage instanceof AdaptationPackageImpl ? (AdaptationPackageImpl)registeredAdaptationPackage : new AdaptationPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theAdaptationPackage.createPackageContents();

		// Initialize created meta-data
		theAdaptationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAdaptationPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AdaptationPackage.eNS_URI, theAdaptationPackage);
		return theAdaptationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationRuleSet() {
		return adaptationRuleSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRuleSet_Name() {
		return (EAttribute)adaptationRuleSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRuleSet_Version() {
		return (EAttribute)adaptationRuleSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationRuleSet_Rules() {
		return (EReference)adaptationRuleSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationRule() {
		return adaptationRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRule_Id() {
		return (EAttribute)adaptationRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRule_Name() {
		return (EAttribute)adaptationRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRule_Version() {
		return (EAttribute)adaptationRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRule_Enabled() {
		return (EAttribute)adaptationRuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationRule_Priority() {
		return (EAttribute)adaptationRuleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationRule_Event() {
		return (EReference)adaptationRuleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationRule_Condition() {
		return (EReference)adaptationRuleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationRule_Actions() {
		return (EReference)adaptationRuleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEvent() {
		return eventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEvent_Type() {
		return (EAttribute)eventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCondition() {
		return conditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComparisonCondition() {
		return comparisonConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComparisonCondition_Attribute() {
		return (EAttribute)comparisonConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComparisonCondition_Operator() {
		return (EAttribute)comparisonConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComparisonCondition_Value() {
		return (EReference)comparisonConditionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLogicalCondition() {
		return logicalConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLogicalCondition_Left() {
		return (EReference)logicalConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogicalCondition_Operator() {
		return (EAttribute)logicalConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLogicalCondition_Right() {
		return (EReference)logicalConditionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNotCondition() {
		return notConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNotCondition_Operand() {
		return (EReference)notConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringValue() {
		return stringValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringValue_Value() {
		return (EAttribute)stringValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntegerValue() {
		return integerValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntegerValue_Value() {
		return (EAttribute)integerValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDecimalValue() {
		return decimalValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDecimalValue_Value() {
		return (EAttribute)decimalValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanValue() {
		return booleanValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanValue_Value() {
		return (EAttribute)booleanValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Type() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Parameters() {
		return (EReference)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationParameters() {
		return adaptationParametersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationParameters_HintLevel() {
		return (EAttribute)adaptationParametersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationParameters_FeedbackStyle() {
		return (EAttribute)adaptationParametersEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationDecision() {
		return adaptationDecisionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationDecision_RuleId() {
		return (EAttribute)adaptationDecisionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationDecision_Actions() {
		return (EReference)adaptationDecisionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationDecision_Explanation() {
		return (EReference)adaptationDecisionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationExplanation() {
		return adaptationExplanationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationExplanation_RuleId() {
		return (EAttribute)adaptationExplanationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationExplanation_Reason() {
		return (EAttribute)adaptationExplanationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdaptationExplanation_Evidence() {
		return (EAttribute)adaptationExplanationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEventType() {
		return eventTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getComparisonOperator() {
		return comparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getLogicalOperator() {
		return logicalOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getActionType() {
		return actionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getHintLevel() {
		return hintLevelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFeedbackStyle() {
		return feedbackStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationFactory getAdaptationFactory() {
		return (AdaptationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		adaptationRuleSetEClass = createEClass(ADAPTATION_RULE_SET);
		createEAttribute(adaptationRuleSetEClass, ADAPTATION_RULE_SET__NAME);
		createEAttribute(adaptationRuleSetEClass, ADAPTATION_RULE_SET__VERSION);
		createEReference(adaptationRuleSetEClass, ADAPTATION_RULE_SET__RULES);

		adaptationRuleEClass = createEClass(ADAPTATION_RULE);
		createEAttribute(adaptationRuleEClass, ADAPTATION_RULE__ID);
		createEAttribute(adaptationRuleEClass, ADAPTATION_RULE__NAME);
		createEAttribute(adaptationRuleEClass, ADAPTATION_RULE__VERSION);
		createEAttribute(adaptationRuleEClass, ADAPTATION_RULE__ENABLED);
		createEAttribute(adaptationRuleEClass, ADAPTATION_RULE__PRIORITY);
		createEReference(adaptationRuleEClass, ADAPTATION_RULE__EVENT);
		createEReference(adaptationRuleEClass, ADAPTATION_RULE__CONDITION);
		createEReference(adaptationRuleEClass, ADAPTATION_RULE__ACTIONS);

		eventEClass = createEClass(EVENT);
		createEAttribute(eventEClass, EVENT__TYPE);

		conditionEClass = createEClass(CONDITION);

		comparisonConditionEClass = createEClass(COMPARISON_CONDITION);
		createEAttribute(comparisonConditionEClass, COMPARISON_CONDITION__ATTRIBUTE);
		createEAttribute(comparisonConditionEClass, COMPARISON_CONDITION__OPERATOR);
		createEReference(comparisonConditionEClass, COMPARISON_CONDITION__VALUE);

		logicalConditionEClass = createEClass(LOGICAL_CONDITION);
		createEReference(logicalConditionEClass, LOGICAL_CONDITION__LEFT);
		createEAttribute(logicalConditionEClass, LOGICAL_CONDITION__OPERATOR);
		createEReference(logicalConditionEClass, LOGICAL_CONDITION__RIGHT);

		notConditionEClass = createEClass(NOT_CONDITION);
		createEReference(notConditionEClass, NOT_CONDITION__OPERAND);

		valueEClass = createEClass(VALUE);

		stringValueEClass = createEClass(STRING_VALUE);
		createEAttribute(stringValueEClass, STRING_VALUE__VALUE);

		integerValueEClass = createEClass(INTEGER_VALUE);
		createEAttribute(integerValueEClass, INTEGER_VALUE__VALUE);

		decimalValueEClass = createEClass(DECIMAL_VALUE);
		createEAttribute(decimalValueEClass, DECIMAL_VALUE__VALUE);

		booleanValueEClass = createEClass(BOOLEAN_VALUE);
		createEAttribute(booleanValueEClass, BOOLEAN_VALUE__VALUE);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__TYPE);
		createEReference(actionEClass, ACTION__PARAMETERS);

		adaptationParametersEClass = createEClass(ADAPTATION_PARAMETERS);
		createEAttribute(adaptationParametersEClass, ADAPTATION_PARAMETERS__HINT_LEVEL);
		createEAttribute(adaptationParametersEClass, ADAPTATION_PARAMETERS__FEEDBACK_STYLE);

		adaptationDecisionEClass = createEClass(ADAPTATION_DECISION);
		createEAttribute(adaptationDecisionEClass, ADAPTATION_DECISION__RULE_ID);
		createEReference(adaptationDecisionEClass, ADAPTATION_DECISION__ACTIONS);
		createEReference(adaptationDecisionEClass, ADAPTATION_DECISION__EXPLANATION);

		adaptationExplanationEClass = createEClass(ADAPTATION_EXPLANATION);
		createEAttribute(adaptationExplanationEClass, ADAPTATION_EXPLANATION__RULE_ID);
		createEAttribute(adaptationExplanationEClass, ADAPTATION_EXPLANATION__REASON);
		createEAttribute(adaptationExplanationEClass, ADAPTATION_EXPLANATION__EVIDENCE);

		// Create enums
		eventTypeEEnum = createEEnum(EVENT_TYPE);
		comparisonOperatorEEnum = createEEnum(COMPARISON_OPERATOR);
		logicalOperatorEEnum = createEEnum(LOGICAL_OPERATOR);
		actionTypeEEnum = createEEnum(ACTION_TYPE);
		hintLevelEEnum = createEEnum(HINT_LEVEL);
		feedbackStyleEEnum = createEEnum(FEEDBACK_STYLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		comparisonConditionEClass.getESuperTypes().add(this.getCondition());
		logicalConditionEClass.getESuperTypes().add(this.getCondition());
		notConditionEClass.getESuperTypes().add(this.getCondition());
		stringValueEClass.getESuperTypes().add(this.getValue());
		integerValueEClass.getESuperTypes().add(this.getValue());
		decimalValueEClass.getESuperTypes().add(this.getValue());
		booleanValueEClass.getESuperTypes().add(this.getValue());

		// Initialize classes, features, and operations; add parameters
		initEClass(adaptationRuleSetEClass, AdaptationRuleSet.class, "AdaptationRuleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationRuleSet_Name(), ecorePackage.getEString(), "name", null, 1, 1, AdaptationRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationRuleSet_Version(), ecorePackage.getEInt(), "version", null, 1, 1, AdaptationRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationRuleSet_Rules(), this.getAdaptationRule(), null, "rules", null, 1, -1, AdaptationRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationRuleEClass, AdaptationRule.class, "AdaptationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationRule_Id(), ecorePackage.getEString(), "id", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationRule_Name(), ecorePackage.getEString(), "name", null, 0, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationRule_Version(), ecorePackage.getEInt(), "version", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationRule_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationRule_Priority(), ecorePackage.getEInt(), "priority", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationRule_Event(), this.getEvent(), null, "event", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationRule_Condition(), this.getCondition(), null, "condition", null, 1, 1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationRule_Actions(), this.getAction(), null, "actions", null, 1, -1, AdaptationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvent_Type(), this.getEventType(), "type", null, 1, 1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEClass, Condition.class, "Condition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(comparisonConditionEClass, ComparisonCondition.class, "ComparisonCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComparisonCondition_Attribute(), ecorePackage.getEString(), "attribute", null, 1, 1, ComparisonCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComparisonCondition_Operator(), this.getComparisonOperator(), "operator", null, 1, 1, ComparisonCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComparisonCondition_Value(), this.getValue(), null, "value", null, 1, 1, ComparisonCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logicalConditionEClass, LogicalCondition.class, "LogicalCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogicalCondition_Left(), this.getCondition(), null, "left", null, 1, 1, LogicalCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogicalCondition_Operator(), this.getLogicalOperator(), "operator", null, 1, 1, LogicalCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLogicalCondition_Right(), this.getCondition(), null, "right", null, 1, 1, LogicalCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notConditionEClass, NotCondition.class, "NotCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNotCondition_Operand(), this.getCondition(), null, "operand", null, 1, 1, NotCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringValueEClass, StringValue.class, "StringValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringValue_Value(), ecorePackage.getEString(), "value", null, 1, 1, StringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerValueEClass, IntegerValue.class, "IntegerValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerValue_Value(), ecorePackage.getELong(), "value", null, 1, 1, IntegerValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decimalValueEClass, DecimalValue.class, "DecimalValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDecimalValue_Value(), ecorePackage.getEBigDecimal(), "value", null, 1, 1, DecimalValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanValueEClass, BooleanValue.class, "BooleanValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanValue_Value(), ecorePackage.getEBoolean(), "value", null, 1, 1, BooleanValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_Type(), this.getActionType(), "type", null, 1, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Parameters(), this.getAdaptationParameters(), null, "parameters", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationParametersEClass, AdaptationParameters.class, "AdaptationParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationParameters_HintLevel(), this.getHintLevel(), "hintLevel", null, 0, 1, AdaptationParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationParameters_FeedbackStyle(), this.getFeedbackStyle(), "feedbackStyle", null, 0, 1, AdaptationParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationDecisionEClass, AdaptationDecision.class, "AdaptationDecision", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationDecision_RuleId(), ecorePackage.getEString(), "ruleId", null, 1, 1, AdaptationDecision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationDecision_Actions(), this.getAction(), null, "actions", null, 1, -1, AdaptationDecision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationDecision_Explanation(), this.getAdaptationExplanation(), null, "explanation", null, 0, 1, AdaptationDecision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationExplanationEClass, AdaptationExplanation.class, "AdaptationExplanation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationExplanation_RuleId(), ecorePackage.getEString(), "ruleId", null, 1, 1, AdaptationExplanation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationExplanation_Reason(), ecorePackage.getEString(), "reason", null, 1, 1, AdaptationExplanation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationExplanation_Evidence(), ecorePackage.getEString(), "evidence", null, 0, -1, AdaptationExplanation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eventTypeEEnum, EventType.class, "EventType");
		addEEnumLiteral(eventTypeEEnum, EventType.ATTEMPT_EVALUATED);

		initEEnum(comparisonOperatorEEnum, ComparisonOperator.class, "ComparisonOperator");
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.EQ);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.NE);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.GT);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.GE);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.LT);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.LE);
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.CONTAINS);

		initEEnum(logicalOperatorEEnum, LogicalOperator.class, "LogicalOperator");
		addEEnumLiteral(logicalOperatorEEnum, LogicalOperator.AND);
		addEEnumLiteral(logicalOperatorEEnum, LogicalOperator.OR);

		initEEnum(actionTypeEEnum, ActionType.class, "ActionType");
		addEEnumLiteral(actionTypeEEnum, ActionType.SHOW_HINT);
		addEEnumLiteral(actionTypeEEnum, ActionType.CHANGE_HINT_LEVEL);
		addEEnumLiteral(actionTypeEEnum, ActionType.REPEAT_ACTIVITY);
		addEEnumLiteral(actionTypeEEnum, ActionType.SELECT_REINFORCEMENT_ACTIVITY);
		addEEnumLiteral(actionTypeEEnum, ActionType.ADVANCE_TO_NEXT_CONCEPT);
		addEEnumLiteral(actionTypeEEnum, ActionType.INCREASE_DIFFICULTY);
		addEEnumLiteral(actionTypeEEnum, ActionType.DECREASE_DIFFICULTY);
		addEEnumLiteral(actionTypeEEnum, ActionType.SHOW_CODE_VIEW);
		addEEnumLiteral(actionTypeEEnum, ActionType.HIDE_CODE_VIEW);
		addEEnumLiteral(actionTypeEEnum, ActionType.CHANGE_FEEDBACK_STYLE);

		initEEnum(hintLevelEEnum, HintLevel.class, "HintLevel");
		addEEnumLiteral(hintLevelEEnum, HintLevel.CONCEPTUAL);
		addEEnumLiteral(hintLevelEEnum, HintLevel.GUIDED);
		addEEnumLiteral(hintLevelEEnum, HintLevel.DIRECT);

		initEEnum(feedbackStyleEEnum, FeedbackStyle.class, "FeedbackStyle");
		addEEnumLiteral(feedbackStyleEEnum, FeedbackStyle.CONCISE);
		addEEnumLiteral(feedbackStyleEEnum, FeedbackStyle.EXPLANATORY);

		// Create resource
		createResource(eNS_URI);
	}

} //AdaptationPackageImpl
