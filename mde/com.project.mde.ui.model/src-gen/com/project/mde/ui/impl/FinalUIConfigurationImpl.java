/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.ActivityLayout;
import com.project.mde.ui.DifficultyMode;
import com.project.mde.ui.FeedbackDetailLevel;
import com.project.mde.ui.FinalUIConfiguration;
import com.project.mde.ui.HintPanelMode;
import com.project.mde.ui.HintStage;
import com.project.mde.ui.NavigationMode;
import com.project.mde.ui.TransitionMode;
import com.project.mde.ui.TutorMode;
import com.project.mde.ui.UiPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Final UI Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getConfigurationVersion <em>Configuration Version</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#isShowCodePanel <em>Show Code Panel</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getHintPanelMode <em>Hint Panel Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getFeedbackDetailLevel <em>Feedback Detail Level</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getActivityLayout <em>Activity Layout</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#isEnabledAssistance <em>Enabled Assistance</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getNavigationMode <em>Navigation Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getDifficultyMode <em>Difficulty Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getTutorMode <em>Tutor Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getTransitionMode <em>Transition Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getNextActivityId <em>Next Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#isRepeatCurrentActivity <em>Repeat Current Activity</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getHintStage <em>Hint Stage</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#getFeedbackMessage <em>Feedback Message</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.FinalUIConfigurationImpl#isGeneratedCodeVisible <em>Generated Code Visible</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FinalUIConfigurationImpl extends MinimalEObjectImpl.Container implements FinalUIConfiguration {
	/**
	 * The default value of the '{@link #getConfigurationVersion() <em>Configuration Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int CONFIGURATION_VERSION_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getConfigurationVersion() <em>Configuration Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationVersion()
	 * @generated
	 * @ordered
	 */
	protected int configurationVersion = CONFIGURATION_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected String activityId = ACTIVITY_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isShowCodePanel() <em>Show Code Panel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShowCodePanel()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHOW_CODE_PANEL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isShowCodePanel() <em>Show Code Panel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShowCodePanel()
	 * @generated
	 * @ordered
	 */
	protected boolean showCodePanel = SHOW_CODE_PANEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getHintPanelMode() <em>Hint Panel Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintPanelMode()
	 * @generated
	 * @ordered
	 */
	protected static final HintPanelMode HINT_PANEL_MODE_EDEFAULT = HintPanelMode.HIDDEN;

	/**
	 * The cached value of the '{@link #getHintPanelMode() <em>Hint Panel Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintPanelMode()
	 * @generated
	 * @ordered
	 */
	protected HintPanelMode hintPanelMode = HINT_PANEL_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFeedbackDetailLevel() <em>Feedback Detail Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackDetailLevel()
	 * @generated
	 * @ordered
	 */
	protected static final FeedbackDetailLevel FEEDBACK_DETAIL_LEVEL_EDEFAULT = FeedbackDetailLevel.STANDARD;

	/**
	 * The cached value of the '{@link #getFeedbackDetailLevel() <em>Feedback Detail Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackDetailLevel()
	 * @generated
	 * @ordered
	 */
	protected FeedbackDetailLevel feedbackDetailLevel = FEEDBACK_DETAIL_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivityLayout() <em>Activity Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityLayout()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityLayout ACTIVITY_LAYOUT_EDEFAULT = ActivityLayout.STANDARD;

	/**
	 * The cached value of the '{@link #getActivityLayout() <em>Activity Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityLayout()
	 * @generated
	 * @ordered
	 */
	protected ActivityLayout activityLayout = ACTIVITY_LAYOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabledAssistance() <em>Enabled Assistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabledAssistance()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_ASSISTANCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnabledAssistance() <em>Enabled Assistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabledAssistance()
	 * @generated
	 * @ordered
	 */
	protected boolean enabledAssistance = ENABLED_ASSISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNavigationMode() <em>Navigation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationMode()
	 * @generated
	 * @ordered
	 */
	protected static final NavigationMode NAVIGATION_MODE_EDEFAULT = NavigationMode.STAY;

	/**
	 * The cached value of the '{@link #getNavigationMode() <em>Navigation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationMode()
	 * @generated
	 * @ordered
	 */
	protected NavigationMode navigationMode = NAVIGATION_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDifficultyMode() <em>Difficulty Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDifficultyMode()
	 * @generated
	 * @ordered
	 */
	protected static final DifficultyMode DIFFICULTY_MODE_EDEFAULT = DifficultyMode.STANDARD;

	/**
	 * The cached value of the '{@link #getDifficultyMode() <em>Difficulty Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDifficultyMode()
	 * @generated
	 * @ordered
	 */
	protected DifficultyMode difficultyMode = DIFFICULTY_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTutorMode() <em>Tutor Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTutorMode()
	 * @generated
	 * @ordered
	 */
	protected static final TutorMode TUTOR_MODE_EDEFAULT = TutorMode.HIDDEN;

	/**
	 * The cached value of the '{@link #getTutorMode() <em>Tutor Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTutorMode()
	 * @generated
	 * @ordered
	 */
	protected TutorMode tutorMode = TUTOR_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransitionMode() <em>Transition Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionMode()
	 * @generated
	 * @ordered
	 */
	protected static final TransitionMode TRANSITION_MODE_EDEFAULT = TransitionMode.NONE;

	/**
	 * The cached value of the '{@link #getTransitionMode() <em>Transition Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionMode()
	 * @generated
	 * @ordered
	 */
	protected TransitionMode transitionMode = TRANSITION_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNextActivityId() <em>Next Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextActivityId()
	 * @generated
	 * @ordered
	 */
	protected static final String NEXT_ACTIVITY_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNextActivityId() <em>Next Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextActivityId()
	 * @generated
	 * @ordered
	 */
	protected String nextActivityId = NEXT_ACTIVITY_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isRepeatCurrentActivity() <em>Repeat Current Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeatCurrentActivity()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPEAT_CURRENT_ACTIVITY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRepeatCurrentActivity() <em>Repeat Current Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeatCurrentActivity()
	 * @generated
	 * @ordered
	 */
	protected boolean repeatCurrentActivity = REPEAT_CURRENT_ACTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getHintStage() <em>Hint Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintStage()
	 * @generated
	 * @ordered
	 */
	protected static final HintStage HINT_STAGE_EDEFAULT = HintStage.NONE;

	/**
	 * The cached value of the '{@link #getHintStage() <em>Hint Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintStage()
	 * @generated
	 * @ordered
	 */
	protected HintStage hintStage = HINT_STAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFeedbackMessage() <em>Feedback Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String FEEDBACK_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeedbackMessage() <em>Feedback Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeedbackMessage()
	 * @generated
	 * @ordered
	 */
	protected String feedbackMessage = FEEDBACK_MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isGeneratedCodeVisible() <em>Generated Code Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGeneratedCodeVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GENERATED_CODE_VISIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGeneratedCodeVisible() <em>Generated Code Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGeneratedCodeVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean generatedCodeVisible = GENERATED_CODE_VISIBLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FinalUIConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiPackage.Literals.FINAL_UI_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getConfigurationVersion() {
		return configurationVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfigurationVersion(int newConfigurationVersion) {
		int oldConfigurationVersion = configurationVersion;
		configurationVersion = newConfigurationVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION, oldConfigurationVersion, configurationVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getActivityId() {
		return activityId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivityId(String newActivityId) {
		String oldActivityId = activityId;
		activityId = newActivityId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_ID, oldActivityId, activityId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isShowCodePanel() {
		return showCodePanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShowCodePanel(boolean newShowCodePanel) {
		boolean oldShowCodePanel = showCodePanel;
		showCodePanel = newShowCodePanel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL, oldShowCodePanel, showCodePanel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HintPanelMode getHintPanelMode() {
		return hintPanelMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHintPanelMode(HintPanelMode newHintPanelMode) {
		HintPanelMode oldHintPanelMode = hintPanelMode;
		hintPanelMode = newHintPanelMode == null ? HINT_PANEL_MODE_EDEFAULT : newHintPanelMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__HINT_PANEL_MODE, oldHintPanelMode, hintPanelMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeedbackDetailLevel getFeedbackDetailLevel() {
		return feedbackDetailLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeedbackDetailLevel(FeedbackDetailLevel newFeedbackDetailLevel) {
		FeedbackDetailLevel oldFeedbackDetailLevel = feedbackDetailLevel;
		feedbackDetailLevel = newFeedbackDetailLevel == null ? FEEDBACK_DETAIL_LEVEL_EDEFAULT : newFeedbackDetailLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL, oldFeedbackDetailLevel, feedbackDetailLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivityLayout getActivityLayout() {
		return activityLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivityLayout(ActivityLayout newActivityLayout) {
		ActivityLayout oldActivityLayout = activityLayout;
		activityLayout = newActivityLayout == null ? ACTIVITY_LAYOUT_EDEFAULT : newActivityLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT, oldActivityLayout, activityLayout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabledAssistance() {
		return enabledAssistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabledAssistance(boolean newEnabledAssistance) {
		boolean oldEnabledAssistance = enabledAssistance;
		enabledAssistance = newEnabledAssistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE, oldEnabledAssistance, enabledAssistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationMode getNavigationMode() {
		return navigationMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNavigationMode(NavigationMode newNavigationMode) {
		NavigationMode oldNavigationMode = navigationMode;
		navigationMode = newNavigationMode == null ? NAVIGATION_MODE_EDEFAULT : newNavigationMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__NAVIGATION_MODE, oldNavigationMode, navigationMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DifficultyMode getDifficultyMode() {
		return difficultyMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDifficultyMode(DifficultyMode newDifficultyMode) {
		DifficultyMode oldDifficultyMode = difficultyMode;
		difficultyMode = newDifficultyMode == null ? DIFFICULTY_MODE_EDEFAULT : newDifficultyMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__DIFFICULTY_MODE, oldDifficultyMode, difficultyMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TutorMode getTutorMode() {
		return tutorMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTutorMode(TutorMode newTutorMode) {
		TutorMode oldTutorMode = tutorMode;
		tutorMode = newTutorMode == null ? TUTOR_MODE_EDEFAULT : newTutorMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__TUTOR_MODE, oldTutorMode, tutorMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransitionMode getTransitionMode() {
		return transitionMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransitionMode(TransitionMode newTransitionMode) {
		TransitionMode oldTransitionMode = transitionMode;
		transitionMode = newTransitionMode == null ? TRANSITION_MODE_EDEFAULT : newTransitionMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__TRANSITION_MODE, oldTransitionMode, transitionMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNextActivityId() {
		return nextActivityId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNextActivityId(String newNextActivityId) {
		String oldNextActivityId = nextActivityId;
		nextActivityId = newNextActivityId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID, oldNextActivityId, nextActivityId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRepeatCurrentActivity() {
		return repeatCurrentActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepeatCurrentActivity(boolean newRepeatCurrentActivity) {
		boolean oldRepeatCurrentActivity = repeatCurrentActivity;
		repeatCurrentActivity = newRepeatCurrentActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY, oldRepeatCurrentActivity, repeatCurrentActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HintStage getHintStage() {
		return hintStage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHintStage(HintStage newHintStage) {
		HintStage oldHintStage = hintStage;
		hintStage = newHintStage == null ? HINT_STAGE_EDEFAULT : newHintStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__HINT_STAGE, oldHintStage, hintStage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeedbackMessage(String newFeedbackMessage) {
		String oldFeedbackMessage = feedbackMessage;
		feedbackMessage = newFeedbackMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE, oldFeedbackMessage, feedbackMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isGeneratedCodeVisible() {
		return generatedCodeVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeneratedCodeVisible(boolean newGeneratedCodeVisible) {
		boolean oldGeneratedCodeVisible = generatedCodeVisible;
		generatedCodeVisible = newGeneratedCodeVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE, oldGeneratedCodeVisible, generatedCodeVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiPackage.FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION:
				return getConfigurationVersion();
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_ID:
				return getActivityId();
			case UiPackage.FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL:
				return isShowCodePanel();
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_PANEL_MODE:
				return getHintPanelMode();
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL:
				return getFeedbackDetailLevel();
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT:
				return getActivityLayout();
			case UiPackage.FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE:
				return isEnabledAssistance();
			case UiPackage.FINAL_UI_CONFIGURATION__NAVIGATION_MODE:
				return getNavigationMode();
			case UiPackage.FINAL_UI_CONFIGURATION__DIFFICULTY_MODE:
				return getDifficultyMode();
			case UiPackage.FINAL_UI_CONFIGURATION__TUTOR_MODE:
				return getTutorMode();
			case UiPackage.FINAL_UI_CONFIGURATION__TRANSITION_MODE:
				return getTransitionMode();
			case UiPackage.FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID:
				return getNextActivityId();
			case UiPackage.FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY:
				return isRepeatCurrentActivity();
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_STAGE:
				return getHintStage();
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE:
				return getFeedbackMessage();
			case UiPackage.FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE:
				return isGeneratedCodeVisible();
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
			case UiPackage.FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION:
				setConfigurationVersion((Integer)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_ID:
				setActivityId((String)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL:
				setShowCodePanel((Boolean)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_PANEL_MODE:
				setHintPanelMode((HintPanelMode)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL:
				setFeedbackDetailLevel((FeedbackDetailLevel)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT:
				setActivityLayout((ActivityLayout)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE:
				setEnabledAssistance((Boolean)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__NAVIGATION_MODE:
				setNavigationMode((NavigationMode)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__DIFFICULTY_MODE:
				setDifficultyMode((DifficultyMode)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__TUTOR_MODE:
				setTutorMode((TutorMode)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__TRANSITION_MODE:
				setTransitionMode((TransitionMode)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID:
				setNextActivityId((String)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY:
				setRepeatCurrentActivity((Boolean)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_STAGE:
				setHintStage((HintStage)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE:
				setFeedbackMessage((String)newValue);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE:
				setGeneratedCodeVisible((Boolean)newValue);
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
			case UiPackage.FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION:
				setConfigurationVersion(CONFIGURATION_VERSION_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_ID:
				setActivityId(ACTIVITY_ID_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL:
				setShowCodePanel(SHOW_CODE_PANEL_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_PANEL_MODE:
				setHintPanelMode(HINT_PANEL_MODE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL:
				setFeedbackDetailLevel(FEEDBACK_DETAIL_LEVEL_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT:
				setActivityLayout(ACTIVITY_LAYOUT_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE:
				setEnabledAssistance(ENABLED_ASSISTANCE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__NAVIGATION_MODE:
				setNavigationMode(NAVIGATION_MODE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__DIFFICULTY_MODE:
				setDifficultyMode(DIFFICULTY_MODE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__TUTOR_MODE:
				setTutorMode(TUTOR_MODE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__TRANSITION_MODE:
				setTransitionMode(TRANSITION_MODE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID:
				setNextActivityId(NEXT_ACTIVITY_ID_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY:
				setRepeatCurrentActivity(REPEAT_CURRENT_ACTIVITY_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_STAGE:
				setHintStage(HINT_STAGE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE:
				setFeedbackMessage(FEEDBACK_MESSAGE_EDEFAULT);
				return;
			case UiPackage.FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE:
				setGeneratedCodeVisible(GENERATED_CODE_VISIBLE_EDEFAULT);
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
			case UiPackage.FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION:
				return configurationVersion != CONFIGURATION_VERSION_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_ID:
				return ACTIVITY_ID_EDEFAULT == null ? activityId != null : !ACTIVITY_ID_EDEFAULT.equals(activityId);
			case UiPackage.FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL:
				return showCodePanel != SHOW_CODE_PANEL_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_PANEL_MODE:
				return hintPanelMode != HINT_PANEL_MODE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL:
				return feedbackDetailLevel != FEEDBACK_DETAIL_LEVEL_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT:
				return activityLayout != ACTIVITY_LAYOUT_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE:
				return enabledAssistance != ENABLED_ASSISTANCE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__NAVIGATION_MODE:
				return navigationMode != NAVIGATION_MODE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__DIFFICULTY_MODE:
				return difficultyMode != DIFFICULTY_MODE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__TUTOR_MODE:
				return tutorMode != TUTOR_MODE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__TRANSITION_MODE:
				return transitionMode != TRANSITION_MODE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID:
				return NEXT_ACTIVITY_ID_EDEFAULT == null ? nextActivityId != null : !NEXT_ACTIVITY_ID_EDEFAULT.equals(nextActivityId);
			case UiPackage.FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY:
				return repeatCurrentActivity != REPEAT_CURRENT_ACTIVITY_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__HINT_STAGE:
				return hintStage != HINT_STAGE_EDEFAULT;
			case UiPackage.FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE:
				return FEEDBACK_MESSAGE_EDEFAULT == null ? feedbackMessage != null : !FEEDBACK_MESSAGE_EDEFAULT.equals(feedbackMessage);
			case UiPackage.FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE:
				return generatedCodeVisible != GENERATED_CODE_VISIBLE_EDEFAULT;
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
		result.append(" (configurationVersion: ");
		result.append(configurationVersion);
		result.append(", activityId: ");
		result.append(activityId);
		result.append(", showCodePanel: ");
		result.append(showCodePanel);
		result.append(", hintPanelMode: ");
		result.append(hintPanelMode);
		result.append(", feedbackDetailLevel: ");
		result.append(feedbackDetailLevel);
		result.append(", activityLayout: ");
		result.append(activityLayout);
		result.append(", enabledAssistance: ");
		result.append(enabledAssistance);
		result.append(", navigationMode: ");
		result.append(navigationMode);
		result.append(", difficultyMode: ");
		result.append(difficultyMode);
		result.append(", tutorMode: ");
		result.append(tutorMode);
		result.append(", transitionMode: ");
		result.append(transitionMode);
		result.append(", nextActivityId: ");
		result.append(nextActivityId);
		result.append(", repeatCurrentActivity: ");
		result.append(repeatCurrentActivity);
		result.append(", hintStage: ");
		result.append(hintStage);
		result.append(", feedbackMessage: ");
		result.append(feedbackMessage);
		result.append(", generatedCodeVisible: ");
		result.append(generatedCodeVisible);
		result.append(')');
		return result.toString();
	}

} //FinalUIConfigurationImpl
