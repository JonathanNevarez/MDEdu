/**
 */
package com.project.mde.adaptation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.project.mde.adaptation.AdaptationFactory
 * @model kind="package"
 * @generated
 */
public interface AdaptationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "adaptation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://mdedu.espoch.edu.ec/model/adaptation/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "adaptation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AdaptationPackage eINSTANCE = com.project.mde.adaptation.impl.AdaptationPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.AdaptationRuleSetImpl <em>Rule Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.AdaptationRuleSetImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationRuleSet()
	 * @generated
	 */
	int ADAPTATION_RULE_SET = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_SET__NAME = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_SET__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_SET__RULES = 2;

	/**
	 * The number of structural features of the '<em>Rule Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_SET_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Rule Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.AdaptationRuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.AdaptationRuleImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationRule()
	 * @generated
	 */
	int ADAPTATION_RULE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__ENABLED = 3;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__PRIORITY = 4;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__EVENT = 5;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__CONDITION = 6;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE__ACTIONS = 7;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.EventImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.ConditionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 3;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.ComparisonConditionImpl <em>Comparison Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.ComparisonConditionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getComparisonCondition()
	 * @generated
	 */
	int COMPARISON_CONDITION = 4;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_CONDITION__ATTRIBUTE = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_CONDITION__OPERATOR = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_CONDITION__VALUE = CONDITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Comparison Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Comparison Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.LogicalConditionImpl <em>Logical Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.LogicalConditionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getLogicalCondition()
	 * @generated
	 */
	int LOGICAL_CONDITION = 5;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_CONDITION__LEFT = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_CONDITION__OPERATOR = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_CONDITION__RIGHT = CONDITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Logical Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Logical Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.NotConditionImpl <em>Not Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.NotConditionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getNotCondition()
	 * @generated
	 */
	int NOT_CONDITION = 6;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_CONDITION__OPERAND = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Not Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Not Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.ValueImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 7;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.StringValueImpl <em>String Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.StringValueImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getStringValue()
	 * @generated
	 */
	int STRING_VALUE = 8;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.IntegerValueImpl <em>Integer Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.IntegerValueImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getIntegerValue()
	 * @generated
	 */
	int INTEGER_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.DecimalValueImpl <em>Decimal Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.DecimalValueImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getDecimalValue()
	 * @generated
	 */
	int DECIMAL_VALUE = 10;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_VALUE__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Decimal Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Decimal Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.BooleanValueImpl <em>Boolean Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.BooleanValueImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getBooleanValue()
	 * @generated
	 */
	int BOOLEAN_VALUE = 11;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.ActionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 12;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PARAMETERS = 1;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.AdaptationParametersImpl <em>Parameters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.AdaptationParametersImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationParameters()
	 * @generated
	 */
	int ADAPTATION_PARAMETERS = 13;

	/**
	 * The feature id for the '<em><b>Hint Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PARAMETERS__HINT_LEVEL = 0;

	/**
	 * The feature id for the '<em><b>Feedback Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PARAMETERS__FEEDBACK_STYLE = 1;

	/**
	 * The number of structural features of the '<em>Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PARAMETERS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PARAMETERS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.AdaptationDecisionImpl <em>Decision</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.AdaptationDecisionImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationDecision()
	 * @generated
	 */
	int ADAPTATION_DECISION = 14;

	/**
	 * The feature id for the '<em><b>Rule Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_DECISION__RULE_ID = 0;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_DECISION__ACTIONS = 1;

	/**
	 * The feature id for the '<em><b>Explanation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_DECISION__EXPLANATION = 2;

	/**
	 * The number of structural features of the '<em>Decision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_DECISION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Decision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_DECISION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.impl.AdaptationExplanationImpl <em>Explanation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.impl.AdaptationExplanationImpl
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationExplanation()
	 * @generated
	 */
	int ADAPTATION_EXPLANATION = 15;

	/**
	 * The feature id for the '<em><b>Rule Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_EXPLANATION__RULE_ID = 0;

	/**
	 * The feature id for the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_EXPLANATION__REASON = 1;

	/**
	 * The feature id for the '<em><b>Evidence</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_EXPLANATION__EVIDENCE = 2;

	/**
	 * The number of structural features of the '<em>Explanation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_EXPLANATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Explanation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_EXPLANATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.EventType <em>Event Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.EventType
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getEventType()
	 * @generated
	 */
	int EVENT_TYPE = 16;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.ComparisonOperator <em>Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.ComparisonOperator
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getComparisonOperator()
	 * @generated
	 */
	int COMPARISON_OPERATOR = 17;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.LogicalOperator <em>Logical Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.LogicalOperator
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getLogicalOperator()
	 * @generated
	 */
	int LOGICAL_OPERATOR = 18;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.ActionType <em>Action Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.ActionType
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getActionType()
	 * @generated
	 */
	int ACTION_TYPE = 19;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.HintLevel <em>Hint Level</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.HintLevel
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getHintLevel()
	 * @generated
	 */
	int HINT_LEVEL = 20;

	/**
	 * The meta object id for the '{@link com.project.mde.adaptation.FeedbackStyle <em>Feedback Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.adaptation.FeedbackStyle
	 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getFeedbackStyle()
	 * @generated
	 */
	int FEEDBACK_STYLE = 21;


	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.AdaptationRuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Set</em>'.
	 * @see com.project.mde.adaptation.AdaptationRuleSet
	 * @generated
	 */
	EClass getAdaptationRuleSet();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRuleSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.project.mde.adaptation.AdaptationRuleSet#getName()
	 * @see #getAdaptationRuleSet()
	 * @generated
	 */
	EAttribute getAdaptationRuleSet_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRuleSet#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.project.mde.adaptation.AdaptationRuleSet#getVersion()
	 * @see #getAdaptationRuleSet()
	 * @generated
	 */
	EAttribute getAdaptationRuleSet_Version();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.adaptation.AdaptationRuleSet#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see com.project.mde.adaptation.AdaptationRuleSet#getRules()
	 * @see #getAdaptationRuleSet()
	 * @generated
	 */
	EReference getAdaptationRuleSet_Rules();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.AdaptationRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule
	 * @generated
	 */
	EClass getAdaptationRule();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRule#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getId()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EAttribute getAdaptationRule_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getName()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EAttribute getAdaptationRule_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRule#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getVersion()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EAttribute getAdaptationRule_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRule#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#isEnabled()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EAttribute getAdaptationRule_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationRule#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getPriority()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EAttribute getAdaptationRule_Priority();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.AdaptationRule#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getEvent()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EReference getAdaptationRule_Event();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.AdaptationRule#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getCondition()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EReference getAdaptationRule_Condition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.adaptation.AdaptationRule#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see com.project.mde.adaptation.AdaptationRule#getActions()
	 * @see #getAdaptationRule()
	 * @generated
	 */
	EReference getAdaptationRule_Actions();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see com.project.mde.adaptation.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.Event#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.project.mde.adaptation.Event#getType()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Type();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see com.project.mde.adaptation.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.ComparisonCondition <em>Comparison Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison Condition</em>'.
	 * @see com.project.mde.adaptation.ComparisonCondition
	 * @generated
	 */
	EClass getComparisonCondition();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.ComparisonCondition#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute</em>'.
	 * @see com.project.mde.adaptation.ComparisonCondition#getAttribute()
	 * @see #getComparisonCondition()
	 * @generated
	 */
	EAttribute getComparisonCondition_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.ComparisonCondition#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see com.project.mde.adaptation.ComparisonCondition#getOperator()
	 * @see #getComparisonCondition()
	 * @generated
	 */
	EAttribute getComparisonCondition_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.ComparisonCondition#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.project.mde.adaptation.ComparisonCondition#getValue()
	 * @see #getComparisonCondition()
	 * @generated
	 */
	EReference getComparisonCondition_Value();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.LogicalCondition <em>Logical Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Condition</em>'.
	 * @see com.project.mde.adaptation.LogicalCondition
	 * @generated
	 */
	EClass getLogicalCondition();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.LogicalCondition#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see com.project.mde.adaptation.LogicalCondition#getLeft()
	 * @see #getLogicalCondition()
	 * @generated
	 */
	EReference getLogicalCondition_Left();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.LogicalCondition#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see com.project.mde.adaptation.LogicalCondition#getOperator()
	 * @see #getLogicalCondition()
	 * @generated
	 */
	EAttribute getLogicalCondition_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.LogicalCondition#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see com.project.mde.adaptation.LogicalCondition#getRight()
	 * @see #getLogicalCondition()
	 * @generated
	 */
	EReference getLogicalCondition_Right();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.NotCondition <em>Not Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Not Condition</em>'.
	 * @see com.project.mde.adaptation.NotCondition
	 * @generated
	 */
	EClass getNotCondition();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.NotCondition#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see com.project.mde.adaptation.NotCondition#getOperand()
	 * @see #getNotCondition()
	 * @generated
	 */
	EReference getNotCondition_Operand();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see com.project.mde.adaptation.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.StringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Value</em>'.
	 * @see com.project.mde.adaptation.StringValue
	 * @generated
	 */
	EClass getStringValue();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.StringValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.project.mde.adaptation.StringValue#getValue()
	 * @see #getStringValue()
	 * @generated
	 */
	EAttribute getStringValue_Value();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.IntegerValue <em>Integer Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Value</em>'.
	 * @see com.project.mde.adaptation.IntegerValue
	 * @generated
	 */
	EClass getIntegerValue();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.IntegerValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.project.mde.adaptation.IntegerValue#getValue()
	 * @see #getIntegerValue()
	 * @generated
	 */
	EAttribute getIntegerValue_Value();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.DecimalValue <em>Decimal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decimal Value</em>'.
	 * @see com.project.mde.adaptation.DecimalValue
	 * @generated
	 */
	EClass getDecimalValue();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.DecimalValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.project.mde.adaptation.DecimalValue#getValue()
	 * @see #getDecimalValue()
	 * @generated
	 */
	EAttribute getDecimalValue_Value();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.BooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Value</em>'.
	 * @see com.project.mde.adaptation.BooleanValue
	 * @generated
	 */
	EClass getBooleanValue();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.BooleanValue#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.project.mde.adaptation.BooleanValue#isValue()
	 * @see #getBooleanValue()
	 * @generated
	 */
	EAttribute getBooleanValue_Value();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see com.project.mde.adaptation.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.Action#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.project.mde.adaptation.Action#getType()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Type();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.Action#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parameters</em>'.
	 * @see com.project.mde.adaptation.Action#getParameters()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Parameters();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.AdaptationParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameters</em>'.
	 * @see com.project.mde.adaptation.AdaptationParameters
	 * @generated
	 */
	EClass getAdaptationParameters();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationParameters#getHintLevel <em>Hint Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Level</em>'.
	 * @see com.project.mde.adaptation.AdaptationParameters#getHintLevel()
	 * @see #getAdaptationParameters()
	 * @generated
	 */
	EAttribute getAdaptationParameters_HintLevel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle <em>Feedback Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feedback Style</em>'.
	 * @see com.project.mde.adaptation.AdaptationParameters#getFeedbackStyle()
	 * @see #getAdaptationParameters()
	 * @generated
	 */
	EAttribute getAdaptationParameters_FeedbackStyle();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.AdaptationDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision</em>'.
	 * @see com.project.mde.adaptation.AdaptationDecision
	 * @generated
	 */
	EClass getAdaptationDecision();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationDecision#getRuleId <em>Rule Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule Id</em>'.
	 * @see com.project.mde.adaptation.AdaptationDecision#getRuleId()
	 * @see #getAdaptationDecision()
	 * @generated
	 */
	EAttribute getAdaptationDecision_RuleId();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.adaptation.AdaptationDecision#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see com.project.mde.adaptation.AdaptationDecision#getActions()
	 * @see #getAdaptationDecision()
	 * @generated
	 */
	EReference getAdaptationDecision_Actions();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.adaptation.AdaptationDecision#getExplanation <em>Explanation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Explanation</em>'.
	 * @see com.project.mde.adaptation.AdaptationDecision#getExplanation()
	 * @see #getAdaptationDecision()
	 * @generated
	 */
	EReference getAdaptationDecision_Explanation();

	/**
	 * Returns the meta object for class '{@link com.project.mde.adaptation.AdaptationExplanation <em>Explanation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explanation</em>'.
	 * @see com.project.mde.adaptation.AdaptationExplanation
	 * @generated
	 */
	EClass getAdaptationExplanation();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationExplanation#getRuleId <em>Rule Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule Id</em>'.
	 * @see com.project.mde.adaptation.AdaptationExplanation#getRuleId()
	 * @see #getAdaptationExplanation()
	 * @generated
	 */
	EAttribute getAdaptationExplanation_RuleId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.adaptation.AdaptationExplanation#getReason <em>Reason</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reason</em>'.
	 * @see com.project.mde.adaptation.AdaptationExplanation#getReason()
	 * @see #getAdaptationExplanation()
	 * @generated
	 */
	EAttribute getAdaptationExplanation_Reason();

	/**
	 * Returns the meta object for the attribute list '{@link com.project.mde.adaptation.AdaptationExplanation#getEvidence <em>Evidence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Evidence</em>'.
	 * @see com.project.mde.adaptation.AdaptationExplanation#getEvidence()
	 * @see #getAdaptationExplanation()
	 * @generated
	 */
	EAttribute getAdaptationExplanation_Evidence();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Event Type</em>'.
	 * @see com.project.mde.adaptation.EventType
	 * @generated
	 */
	EEnum getEventType();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.ComparisonOperator <em>Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Comparison Operator</em>'.
	 * @see com.project.mde.adaptation.ComparisonOperator
	 * @generated
	 */
	EEnum getComparisonOperator();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.LogicalOperator <em>Logical Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Logical Operator</em>'.
	 * @see com.project.mde.adaptation.LogicalOperator
	 * @generated
	 */
	EEnum getLogicalOperator();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.ActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Type</em>'.
	 * @see com.project.mde.adaptation.ActionType
	 * @generated
	 */
	EEnum getActionType();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.HintLevel <em>Hint Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hint Level</em>'.
	 * @see com.project.mde.adaptation.HintLevel
	 * @generated
	 */
	EEnum getHintLevel();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.adaptation.FeedbackStyle <em>Feedback Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Feedback Style</em>'.
	 * @see com.project.mde.adaptation.FeedbackStyle
	 * @generated
	 */
	EEnum getFeedbackStyle();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AdaptationFactory getAdaptationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.AdaptationRuleSetImpl <em>Rule Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.AdaptationRuleSetImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationRuleSet()
		 * @generated
		 */
		EClass ADAPTATION_RULE_SET = eINSTANCE.getAdaptationRuleSet();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE_SET__NAME = eINSTANCE.getAdaptationRuleSet_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE_SET__VERSION = eINSTANCE.getAdaptationRuleSet_Version();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_RULE_SET__RULES = eINSTANCE.getAdaptationRuleSet_Rules();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.AdaptationRuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.AdaptationRuleImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationRule()
		 * @generated
		 */
		EClass ADAPTATION_RULE = eINSTANCE.getAdaptationRule();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE__ID = eINSTANCE.getAdaptationRule_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE__NAME = eINSTANCE.getAdaptationRule_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE__VERSION = eINSTANCE.getAdaptationRule_Version();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE__ENABLED = eINSTANCE.getAdaptationRule_Enabled();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_RULE__PRIORITY = eINSTANCE.getAdaptationRule_Priority();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_RULE__EVENT = eINSTANCE.getAdaptationRule_Event();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_RULE__CONDITION = eINSTANCE.getAdaptationRule_Condition();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_RULE__ACTIONS = eINSTANCE.getAdaptationRule_Actions();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.EventImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__TYPE = eINSTANCE.getEvent_Type();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.ConditionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.ComparisonConditionImpl <em>Comparison Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.ComparisonConditionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getComparisonCondition()
		 * @generated
		 */
		EClass COMPARISON_CONDITION = eINSTANCE.getComparisonCondition();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARISON_CONDITION__ATTRIBUTE = eINSTANCE.getComparisonCondition_Attribute();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARISON_CONDITION__OPERATOR = eINSTANCE.getComparisonCondition_Operator();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_CONDITION__VALUE = eINSTANCE.getComparisonCondition_Value();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.LogicalConditionImpl <em>Logical Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.LogicalConditionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getLogicalCondition()
		 * @generated
		 */
		EClass LOGICAL_CONDITION = eINSTANCE.getLogicalCondition();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_CONDITION__LEFT = eINSTANCE.getLogicalCondition_Left();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGICAL_CONDITION__OPERATOR = eINSTANCE.getLogicalCondition_Operator();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_CONDITION__RIGHT = eINSTANCE.getLogicalCondition_Right();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.NotConditionImpl <em>Not Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.NotConditionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getNotCondition()
		 * @generated
		 */
		EClass NOT_CONDITION = eINSTANCE.getNotCondition();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOT_CONDITION__OPERAND = eINSTANCE.getNotCondition_Operand();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.ValueImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.StringValueImpl <em>String Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.StringValueImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getStringValue()
		 * @generated
		 */
		EClass STRING_VALUE = eINSTANCE.getStringValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VALUE__VALUE = eINSTANCE.getStringValue_Value();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.IntegerValueImpl <em>Integer Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.IntegerValueImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getIntegerValue()
		 * @generated
		 */
		EClass INTEGER_VALUE = eINSTANCE.getIntegerValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_VALUE__VALUE = eINSTANCE.getIntegerValue_Value();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.DecimalValueImpl <em>Decimal Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.DecimalValueImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getDecimalValue()
		 * @generated
		 */
		EClass DECIMAL_VALUE = eINSTANCE.getDecimalValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECIMAL_VALUE__VALUE = eINSTANCE.getDecimalValue_Value();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.BooleanValueImpl <em>Boolean Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.BooleanValueImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getBooleanValue()
		 * @generated
		 */
		EClass BOOLEAN_VALUE = eINSTANCE.getBooleanValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VALUE__VALUE = eINSTANCE.getBooleanValue_Value();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.ActionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__TYPE = eINSTANCE.getAction_Type();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__PARAMETERS = eINSTANCE.getAction_Parameters();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.AdaptationParametersImpl <em>Parameters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.AdaptationParametersImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationParameters()
		 * @generated
		 */
		EClass ADAPTATION_PARAMETERS = eINSTANCE.getAdaptationParameters();

		/**
		 * The meta object literal for the '<em><b>Hint Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_PARAMETERS__HINT_LEVEL = eINSTANCE.getAdaptationParameters_HintLevel();

		/**
		 * The meta object literal for the '<em><b>Feedback Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_PARAMETERS__FEEDBACK_STYLE = eINSTANCE.getAdaptationParameters_FeedbackStyle();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.AdaptationDecisionImpl <em>Decision</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.AdaptationDecisionImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationDecision()
		 * @generated
		 */
		EClass ADAPTATION_DECISION = eINSTANCE.getAdaptationDecision();

		/**
		 * The meta object literal for the '<em><b>Rule Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_DECISION__RULE_ID = eINSTANCE.getAdaptationDecision_RuleId();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_DECISION__ACTIONS = eINSTANCE.getAdaptationDecision_Actions();

		/**
		 * The meta object literal for the '<em><b>Explanation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_DECISION__EXPLANATION = eINSTANCE.getAdaptationDecision_Explanation();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.impl.AdaptationExplanationImpl <em>Explanation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.impl.AdaptationExplanationImpl
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getAdaptationExplanation()
		 * @generated
		 */
		EClass ADAPTATION_EXPLANATION = eINSTANCE.getAdaptationExplanation();

		/**
		 * The meta object literal for the '<em><b>Rule Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_EXPLANATION__RULE_ID = eINSTANCE.getAdaptationExplanation_RuleId();

		/**
		 * The meta object literal for the '<em><b>Reason</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_EXPLANATION__REASON = eINSTANCE.getAdaptationExplanation_Reason();

		/**
		 * The meta object literal for the '<em><b>Evidence</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_EXPLANATION__EVIDENCE = eINSTANCE.getAdaptationExplanation_Evidence();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.EventType <em>Event Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.EventType
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getEventType()
		 * @generated
		 */
		EEnum EVENT_TYPE = eINSTANCE.getEventType();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.ComparisonOperator <em>Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.ComparisonOperator
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getComparisonOperator()
		 * @generated
		 */
		EEnum COMPARISON_OPERATOR = eINSTANCE.getComparisonOperator();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.LogicalOperator <em>Logical Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.LogicalOperator
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getLogicalOperator()
		 * @generated
		 */
		EEnum LOGICAL_OPERATOR = eINSTANCE.getLogicalOperator();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.ActionType <em>Action Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.ActionType
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getActionType()
		 * @generated
		 */
		EEnum ACTION_TYPE = eINSTANCE.getActionType();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.HintLevel <em>Hint Level</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.HintLevel
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getHintLevel()
		 * @generated
		 */
		EEnum HINT_LEVEL = eINSTANCE.getHintLevel();

		/**
		 * The meta object literal for the '{@link com.project.mde.adaptation.FeedbackStyle <em>Feedback Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.adaptation.FeedbackStyle
		 * @see com.project.mde.adaptation.impl.AdaptationPackageImpl#getFeedbackStyle()
		 * @generated
		 */
		EEnum FEEDBACK_STYLE = eINSTANCE.getFeedbackStyle();

	}

} //AdaptationPackage
