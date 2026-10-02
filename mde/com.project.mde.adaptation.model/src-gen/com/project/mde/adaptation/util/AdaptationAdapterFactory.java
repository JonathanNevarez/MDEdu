/**
 */
package com.project.mde.adaptation.util;

import com.project.mde.adaptation.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.project.mde.adaptation.AdaptationPackage
 * @generated
 */
public class AdaptationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AdaptationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AdaptationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationSwitch<Adapter> modelSwitch =
		new AdaptationSwitch<Adapter>() {
			@Override
			public Adapter caseAdaptationRuleSet(AdaptationRuleSet object) {
				return createAdaptationRuleSetAdapter();
			}
			@Override
			public Adapter caseAdaptationRule(AdaptationRule object) {
				return createAdaptationRuleAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseCondition(Condition object) {
				return createConditionAdapter();
			}
			@Override
			public Adapter caseComparisonCondition(ComparisonCondition object) {
				return createComparisonConditionAdapter();
			}
			@Override
			public Adapter caseLogicalCondition(LogicalCondition object) {
				return createLogicalConditionAdapter();
			}
			@Override
			public Adapter caseNotCondition(NotCondition object) {
				return createNotConditionAdapter();
			}
			@Override
			public Adapter caseValue(Value object) {
				return createValueAdapter();
			}
			@Override
			public Adapter caseStringValue(StringValue object) {
				return createStringValueAdapter();
			}
			@Override
			public Adapter caseIntegerValue(IntegerValue object) {
				return createIntegerValueAdapter();
			}
			@Override
			public Adapter caseDecimalValue(DecimalValue object) {
				return createDecimalValueAdapter();
			}
			@Override
			public Adapter caseBooleanValue(BooleanValue object) {
				return createBooleanValueAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseAdaptationParameters(AdaptationParameters object) {
				return createAdaptationParametersAdapter();
			}
			@Override
			public Adapter caseAdaptationDecision(AdaptationDecision object) {
				return createAdaptationDecisionAdapter();
			}
			@Override
			public Adapter caseAdaptationExplanation(AdaptationExplanation object) {
				return createAdaptationExplanationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.AdaptationRuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.AdaptationRuleSet
	 * @generated
	 */
	public Adapter createAdaptationRuleSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.AdaptationRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.AdaptationRule
	 * @generated
	 */
	public Adapter createAdaptationRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.Condition
	 * @generated
	 */
	public Adapter createConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.ComparisonCondition <em>Comparison Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.ComparisonCondition
	 * @generated
	 */
	public Adapter createComparisonConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.LogicalCondition <em>Logical Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.LogicalCondition
	 * @generated
	 */
	public Adapter createLogicalConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.NotCondition <em>Not Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.NotCondition
	 * @generated
	 */
	public Adapter createNotConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.StringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.StringValue
	 * @generated
	 */
	public Adapter createStringValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.IntegerValue <em>Integer Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.IntegerValue
	 * @generated
	 */
	public Adapter createIntegerValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.DecimalValue <em>Decimal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.DecimalValue
	 * @generated
	 */
	public Adapter createDecimalValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.BooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.BooleanValue
	 * @generated
	 */
	public Adapter createBooleanValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.AdaptationParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.AdaptationParameters
	 * @generated
	 */
	public Adapter createAdaptationParametersAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.AdaptationDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.AdaptationDecision
	 * @generated
	 */
	public Adapter createAdaptationDecisionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.project.mde.adaptation.AdaptationExplanation <em>Explanation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.project.mde.adaptation.AdaptationExplanation
	 * @generated
	 */
	public Adapter createAdaptationExplanationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AdaptationAdapterFactory
