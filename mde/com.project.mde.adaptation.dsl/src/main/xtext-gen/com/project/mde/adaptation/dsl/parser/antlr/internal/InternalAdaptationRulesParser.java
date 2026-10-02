package com.project.mde.adaptation.dsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.project.mde.adaptation.dsl.services.AdaptationRulesGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalAdaptationRulesParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'ruleset'", "'version'", "'rule'", "'name'", "'enabled'", "'on'", "'when'", "'then'", "'priority'", "'not'", "'('", "')'", "'-'", "'.'", "'true'", "'false'", "'action'", "'level'", "'style'", "'ATTEMPT_EVALUATED'", "'or'", "'and'", "'=='", "'!='", "'>'", "'>='", "'<'", "'<='", "'contains'", "'SHOW_HINT'", "'CHANGE_HINT_LEVEL'", "'REPEAT_ACTIVITY'", "'SELECT_REINFORCEMENT_ACTIVITY'", "'ADVANCE_TO_NEXT_CONCEPT'", "'INCREASE_DIFFICULTY'", "'DECREASE_DIFFICULTY'", "'SHOW_CODE_VIEW'", "'HIDE_CODE_VIEW'", "'CHANGE_FEEDBACK_STYLE'", "'CONCEPTUAL'", "'GUIDED'", "'DIRECT'", "'CONCISE'", "'EXPLANATORY'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalAdaptationRulesParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalAdaptationRulesParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalAdaptationRulesParser.tokenNames; }
    public String getGrammarFileName() { return "InternalAdaptationRules.g"; }



     	private AdaptationRulesGrammarAccess grammarAccess;

        public InternalAdaptationRulesParser(TokenStream input, AdaptationRulesGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "AdaptationRuleSet";
       	}

       	@Override
       	protected AdaptationRulesGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleAdaptationRuleSet"
    // InternalAdaptationRules.g:65:1: entryRuleAdaptationRuleSet returns [EObject current=null] : iv_ruleAdaptationRuleSet= ruleAdaptationRuleSet EOF ;
    public final EObject entryRuleAdaptationRuleSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdaptationRuleSet = null;


        try {
            // InternalAdaptationRules.g:65:58: (iv_ruleAdaptationRuleSet= ruleAdaptationRuleSet EOF )
            // InternalAdaptationRules.g:66:2: iv_ruleAdaptationRuleSet= ruleAdaptationRuleSet EOF
            {
             newCompositeNode(grammarAccess.getAdaptationRuleSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdaptationRuleSet=ruleAdaptationRuleSet();

            state._fsp--;

             current =iv_ruleAdaptationRuleSet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdaptationRuleSet"


    // $ANTLR start "ruleAdaptationRuleSet"
    // InternalAdaptationRules.g:72:1: ruleAdaptationRuleSet returns [EObject current=null] : (otherlv_0= 'ruleset' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= 'version' ( (lv_version_3_0= ruleSignedInt ) ) ( (lv_rules_4_0= ruleAdaptationRule ) )* ) ;
    public final EObject ruleAdaptationRuleSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_version_3_0 = null;

        EObject lv_rules_4_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:78:2: ( (otherlv_0= 'ruleset' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= 'version' ( (lv_version_3_0= ruleSignedInt ) ) ( (lv_rules_4_0= ruleAdaptationRule ) )* ) )
            // InternalAdaptationRules.g:79:2: (otherlv_0= 'ruleset' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= 'version' ( (lv_version_3_0= ruleSignedInt ) ) ( (lv_rules_4_0= ruleAdaptationRule ) )* )
            {
            // InternalAdaptationRules.g:79:2: (otherlv_0= 'ruleset' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= 'version' ( (lv_version_3_0= ruleSignedInt ) ) ( (lv_rules_4_0= ruleAdaptationRule ) )* )
            // InternalAdaptationRules.g:80:3: otherlv_0= 'ruleset' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= 'version' ( (lv_version_3_0= ruleSignedInt ) ) ( (lv_rules_4_0= ruleAdaptationRule ) )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getAdaptationRuleSetAccess().getRulesetKeyword_0());
            		
            // InternalAdaptationRules.g:84:3: ( (lv_name_1_0= RULE_STRING ) )
            // InternalAdaptationRules.g:85:4: (lv_name_1_0= RULE_STRING )
            {
            // InternalAdaptationRules.g:85:4: (lv_name_1_0= RULE_STRING )
            // InternalAdaptationRules.g:86:5: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getAdaptationRuleSetAccess().getNameSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAdaptationRuleSetRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getAdaptationRuleSetAccess().getVersionKeyword_2());
            		
            // InternalAdaptationRules.g:106:3: ( (lv_version_3_0= ruleSignedInt ) )
            // InternalAdaptationRules.g:107:4: (lv_version_3_0= ruleSignedInt )
            {
            // InternalAdaptationRules.g:107:4: (lv_version_3_0= ruleSignedInt )
            // InternalAdaptationRules.g:108:5: lv_version_3_0= ruleSignedInt
            {

            					newCompositeNode(grammarAccess.getAdaptationRuleSetAccess().getVersionSignedIntParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_version_3_0=ruleSignedInt();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdaptationRuleSetRule());
            					}
            					set(
            						current,
            						"version",
            						lv_version_3_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.SignedInt");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalAdaptationRules.g:125:3: ( (lv_rules_4_0= ruleAdaptationRule ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalAdaptationRules.g:126:4: (lv_rules_4_0= ruleAdaptationRule )
            	    {
            	    // InternalAdaptationRules.g:126:4: (lv_rules_4_0= ruleAdaptationRule )
            	    // InternalAdaptationRules.g:127:5: lv_rules_4_0= ruleAdaptationRule
            	    {

            	    					newCompositeNode(grammarAccess.getAdaptationRuleSetAccess().getRulesAdaptationRuleParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_rules_4_0=ruleAdaptationRule();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAdaptationRuleSetRule());
            	    					}
            	    					add(
            	    						current,
            	    						"rules",
            	    						lv_rules_4_0,
            	    						"com.project.mde.adaptation.dsl.AdaptationRules.AdaptationRule");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdaptationRuleSet"


    // $ANTLR start "entryRuleAdaptationRule"
    // InternalAdaptationRules.g:148:1: entryRuleAdaptationRule returns [EObject current=null] : iv_ruleAdaptationRule= ruleAdaptationRule EOF ;
    public final EObject entryRuleAdaptationRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdaptationRule = null;


        try {
            // InternalAdaptationRules.g:148:55: (iv_ruleAdaptationRule= ruleAdaptationRule EOF )
            // InternalAdaptationRules.g:149:2: iv_ruleAdaptationRule= ruleAdaptationRule EOF
            {
             newCompositeNode(grammarAccess.getAdaptationRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdaptationRule=ruleAdaptationRule();

            state._fsp--;

             current =iv_ruleAdaptationRule; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdaptationRule"


    // $ANTLR start "ruleAdaptationRule"
    // InternalAdaptationRules.g:155:1: ruleAdaptationRule returns [EObject current=null] : (otherlv_0= 'rule' ( (lv_id_1_0= RULE_ID ) ) (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )? otherlv_4= 'version' ( (lv_version_5_0= ruleSignedInt ) ) otherlv_6= 'enabled' ( (lv_enabled_7_0= ruleBoolean ) ) otherlv_8= 'on' ( (lv_event_9_0= ruleEvent ) ) otherlv_10= 'when' ( (lv_condition_11_0= ruleCondition ) )? otherlv_12= 'then' ( (lv_actions_13_0= ruleAction ) )* otherlv_14= 'priority' ( (lv_priority_15_0= ruleSignedInt ) ) ) ;
    public final EObject ruleAdaptationRule() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        AntlrDatatypeRuleToken lv_version_5_0 = null;

        AntlrDatatypeRuleToken lv_enabled_7_0 = null;

        EObject lv_event_9_0 = null;

        EObject lv_condition_11_0 = null;

        EObject lv_actions_13_0 = null;

        AntlrDatatypeRuleToken lv_priority_15_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:161:2: ( (otherlv_0= 'rule' ( (lv_id_1_0= RULE_ID ) ) (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )? otherlv_4= 'version' ( (lv_version_5_0= ruleSignedInt ) ) otherlv_6= 'enabled' ( (lv_enabled_7_0= ruleBoolean ) ) otherlv_8= 'on' ( (lv_event_9_0= ruleEvent ) ) otherlv_10= 'when' ( (lv_condition_11_0= ruleCondition ) )? otherlv_12= 'then' ( (lv_actions_13_0= ruleAction ) )* otherlv_14= 'priority' ( (lv_priority_15_0= ruleSignedInt ) ) ) )
            // InternalAdaptationRules.g:162:2: (otherlv_0= 'rule' ( (lv_id_1_0= RULE_ID ) ) (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )? otherlv_4= 'version' ( (lv_version_5_0= ruleSignedInt ) ) otherlv_6= 'enabled' ( (lv_enabled_7_0= ruleBoolean ) ) otherlv_8= 'on' ( (lv_event_9_0= ruleEvent ) ) otherlv_10= 'when' ( (lv_condition_11_0= ruleCondition ) )? otherlv_12= 'then' ( (lv_actions_13_0= ruleAction ) )* otherlv_14= 'priority' ( (lv_priority_15_0= ruleSignedInt ) ) )
            {
            // InternalAdaptationRules.g:162:2: (otherlv_0= 'rule' ( (lv_id_1_0= RULE_ID ) ) (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )? otherlv_4= 'version' ( (lv_version_5_0= ruleSignedInt ) ) otherlv_6= 'enabled' ( (lv_enabled_7_0= ruleBoolean ) ) otherlv_8= 'on' ( (lv_event_9_0= ruleEvent ) ) otherlv_10= 'when' ( (lv_condition_11_0= ruleCondition ) )? otherlv_12= 'then' ( (lv_actions_13_0= ruleAction ) )* otherlv_14= 'priority' ( (lv_priority_15_0= ruleSignedInt ) ) )
            // InternalAdaptationRules.g:163:3: otherlv_0= 'rule' ( (lv_id_1_0= RULE_ID ) ) (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )? otherlv_4= 'version' ( (lv_version_5_0= ruleSignedInt ) ) otherlv_6= 'enabled' ( (lv_enabled_7_0= ruleBoolean ) ) otherlv_8= 'on' ( (lv_event_9_0= ruleEvent ) ) otherlv_10= 'when' ( (lv_condition_11_0= ruleCondition ) )? otherlv_12= 'then' ( (lv_actions_13_0= ruleAction ) )* otherlv_14= 'priority' ( (lv_priority_15_0= ruleSignedInt ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getAdaptationRuleAccess().getRuleKeyword_0());
            		
            // InternalAdaptationRules.g:167:3: ( (lv_id_1_0= RULE_ID ) )
            // InternalAdaptationRules.g:168:4: (lv_id_1_0= RULE_ID )
            {
            // InternalAdaptationRules.g:168:4: (lv_id_1_0= RULE_ID )
            // InternalAdaptationRules.g:169:5: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_id_1_0, grammarAccess.getAdaptationRuleAccess().getIdIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAdaptationRuleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalAdaptationRules.g:185:3: (otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalAdaptationRules.g:186:4: otherlv_2= 'name' ( (lv_name_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,14,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getAdaptationRuleAccess().getNameKeyword_2_0());
                    			
                    // InternalAdaptationRules.g:190:4: ( (lv_name_3_0= RULE_STRING ) )
                    // InternalAdaptationRules.g:191:5: (lv_name_3_0= RULE_STRING )
                    {
                    // InternalAdaptationRules.g:191:5: (lv_name_3_0= RULE_STRING )
                    // InternalAdaptationRules.g:192:6: lv_name_3_0= RULE_STRING
                    {
                    lv_name_3_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

                    						newLeafNode(lv_name_3_0, grammarAccess.getAdaptationRuleAccess().getNameSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAdaptationRuleRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"name",
                    							lv_name_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_4, grammarAccess.getAdaptationRuleAccess().getVersionKeyword_3());
            		
            // InternalAdaptationRules.g:213:3: ( (lv_version_5_0= ruleSignedInt ) )
            // InternalAdaptationRules.g:214:4: (lv_version_5_0= ruleSignedInt )
            {
            // InternalAdaptationRules.g:214:4: (lv_version_5_0= ruleSignedInt )
            // InternalAdaptationRules.g:215:5: lv_version_5_0= ruleSignedInt
            {

            					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getVersionSignedIntParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_9);
            lv_version_5_0=ruleSignedInt();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
            					}
            					set(
            						current,
            						"version",
            						lv_version_5_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.SignedInt");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_10); 

            			newLeafNode(otherlv_6, grammarAccess.getAdaptationRuleAccess().getEnabledKeyword_5());
            		
            // InternalAdaptationRules.g:236:3: ( (lv_enabled_7_0= ruleBoolean ) )
            // InternalAdaptationRules.g:237:4: (lv_enabled_7_0= ruleBoolean )
            {
            // InternalAdaptationRules.g:237:4: (lv_enabled_7_0= ruleBoolean )
            // InternalAdaptationRules.g:238:5: lv_enabled_7_0= ruleBoolean
            {

            					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getEnabledBooleanParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_11);
            lv_enabled_7_0=ruleBoolean();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
            					}
            					set(
            						current,
            						"enabled",
            						lv_enabled_7_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.Boolean");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,16,FOLLOW_12); 

            			newLeafNode(otherlv_8, grammarAccess.getAdaptationRuleAccess().getOnKeyword_7());
            		
            // InternalAdaptationRules.g:259:3: ( (lv_event_9_0= ruleEvent ) )
            // InternalAdaptationRules.g:260:4: (lv_event_9_0= ruleEvent )
            {
            // InternalAdaptationRules.g:260:4: (lv_event_9_0= ruleEvent )
            // InternalAdaptationRules.g:261:5: lv_event_9_0= ruleEvent
            {

            					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getEventEventParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_13);
            lv_event_9_0=ruleEvent();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
            					}
            					set(
            						current,
            						"event",
            						lv_event_9_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.Event");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,17,FOLLOW_14); 

            			newLeafNode(otherlv_10, grammarAccess.getAdaptationRuleAccess().getWhenKeyword_9());
            		
            // InternalAdaptationRules.g:282:3: ( (lv_condition_11_0= ruleCondition ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID||(LA3_0>=20 && LA3_0<=21)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalAdaptationRules.g:283:4: (lv_condition_11_0= ruleCondition )
                    {
                    // InternalAdaptationRules.g:283:4: (lv_condition_11_0= ruleCondition )
                    // InternalAdaptationRules.g:284:5: lv_condition_11_0= ruleCondition
                    {

                    					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getConditionConditionParserRuleCall_10_0());
                    				
                    pushFollow(FOLLOW_15);
                    lv_condition_11_0=ruleCondition();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
                    					}
                    					set(
                    						current,
                    						"condition",
                    						lv_condition_11_0,
                    						"com.project.mde.adaptation.dsl.AdaptationRules.Condition");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,18,FOLLOW_16); 

            			newLeafNode(otherlv_12, grammarAccess.getAdaptationRuleAccess().getThenKeyword_11());
            		
            // InternalAdaptationRules.g:305:3: ( (lv_actions_13_0= ruleAction ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==27) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalAdaptationRules.g:306:4: (lv_actions_13_0= ruleAction )
            	    {
            	    // InternalAdaptationRules.g:306:4: (lv_actions_13_0= ruleAction )
            	    // InternalAdaptationRules.g:307:5: lv_actions_13_0= ruleAction
            	    {

            	    					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getActionsActionParserRuleCall_12_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_actions_13_0=ruleAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
            	    					}
            	    					add(
            	    						current,
            	    						"actions",
            	    						lv_actions_13_0,
            	    						"com.project.mde.adaptation.dsl.AdaptationRules.Action");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_14=(Token)match(input,19,FOLLOW_5); 

            			newLeafNode(otherlv_14, grammarAccess.getAdaptationRuleAccess().getPriorityKeyword_13());
            		
            // InternalAdaptationRules.g:328:3: ( (lv_priority_15_0= ruleSignedInt ) )
            // InternalAdaptationRules.g:329:4: (lv_priority_15_0= ruleSignedInt )
            {
            // InternalAdaptationRules.g:329:4: (lv_priority_15_0= ruleSignedInt )
            // InternalAdaptationRules.g:330:5: lv_priority_15_0= ruleSignedInt
            {

            					newCompositeNode(grammarAccess.getAdaptationRuleAccess().getPrioritySignedIntParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_priority_15_0=ruleSignedInt();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdaptationRuleRule());
            					}
            					set(
            						current,
            						"priority",
            						lv_priority_15_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.SignedInt");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdaptationRule"


    // $ANTLR start "entryRuleEvent"
    // InternalAdaptationRules.g:351:1: entryRuleEvent returns [EObject current=null] : iv_ruleEvent= ruleEvent EOF ;
    public final EObject entryRuleEvent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEvent = null;


        try {
            // InternalAdaptationRules.g:351:46: (iv_ruleEvent= ruleEvent EOF )
            // InternalAdaptationRules.g:352:2: iv_ruleEvent= ruleEvent EOF
            {
             newCompositeNode(grammarAccess.getEventRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEvent=ruleEvent();

            state._fsp--;

             current =iv_ruleEvent; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEvent"


    // $ANTLR start "ruleEvent"
    // InternalAdaptationRules.g:358:1: ruleEvent returns [EObject current=null] : ( (lv_type_0_0= ruleEventType ) ) ;
    public final EObject ruleEvent() throws RecognitionException {
        EObject current = null;

        Enumerator lv_type_0_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:364:2: ( ( (lv_type_0_0= ruleEventType ) ) )
            // InternalAdaptationRules.g:365:2: ( (lv_type_0_0= ruleEventType ) )
            {
            // InternalAdaptationRules.g:365:2: ( (lv_type_0_0= ruleEventType ) )
            // InternalAdaptationRules.g:366:3: (lv_type_0_0= ruleEventType )
            {
            // InternalAdaptationRules.g:366:3: (lv_type_0_0= ruleEventType )
            // InternalAdaptationRules.g:367:4: lv_type_0_0= ruleEventType
            {

            				newCompositeNode(grammarAccess.getEventAccess().getTypeEventTypeEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_type_0_0=ruleEventType();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getEventRule());
            				}
            				set(
            					current,
            					"type",
            					lv_type_0_0,
            					"com.project.mde.adaptation.dsl.AdaptationRules.EventType");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEvent"


    // $ANTLR start "entryRuleCondition"
    // InternalAdaptationRules.g:387:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalAdaptationRules.g:387:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalAdaptationRules.g:388:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalAdaptationRules.g:394:1: ruleCondition returns [EObject current=null] : this_OrCondition_0= ruleOrCondition ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        EObject this_OrCondition_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:400:2: (this_OrCondition_0= ruleOrCondition )
            // InternalAdaptationRules.g:401:2: this_OrCondition_0= ruleOrCondition
            {

            		newCompositeNode(grammarAccess.getConditionAccess().getOrConditionParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_OrCondition_0=ruleOrCondition();

            state._fsp--;


            		current = this_OrCondition_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleOrCondition"
    // InternalAdaptationRules.g:412:1: entryRuleOrCondition returns [EObject current=null] : iv_ruleOrCondition= ruleOrCondition EOF ;
    public final EObject entryRuleOrCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrCondition = null;


        try {
            // InternalAdaptationRules.g:412:52: (iv_ruleOrCondition= ruleOrCondition EOF )
            // InternalAdaptationRules.g:413:2: iv_ruleOrCondition= ruleOrCondition EOF
            {
             newCompositeNode(grammarAccess.getOrConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrCondition=ruleOrCondition();

            state._fsp--;

             current =iv_ruleOrCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrCondition"


    // $ANTLR start "ruleOrCondition"
    // InternalAdaptationRules.g:419:1: ruleOrCondition returns [EObject current=null] : (this_AndCondition_0= ruleAndCondition ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )* ) ;
    public final EObject ruleOrCondition() throws RecognitionException {
        EObject current = null;

        EObject this_AndCondition_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:425:2: ( (this_AndCondition_0= ruleAndCondition ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )* ) )
            // InternalAdaptationRules.g:426:2: (this_AndCondition_0= ruleAndCondition ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )* )
            {
            // InternalAdaptationRules.g:426:2: (this_AndCondition_0= ruleAndCondition ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )* )
            // InternalAdaptationRules.g:427:3: this_AndCondition_0= ruleAndCondition ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )*
            {

            			newCompositeNode(grammarAccess.getOrConditionAccess().getAndConditionParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_AndCondition_0=ruleAndCondition();

            state._fsp--;


            			current = this_AndCondition_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalAdaptationRules.g:435:3: ( () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==31) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalAdaptationRules.g:436:4: () ( (lv_operator_2_0= ruleOr ) ) ( (lv_right_3_0= ruleAndCondition ) )
            	    {
            	    // InternalAdaptationRules.g:436:4: ()
            	    // InternalAdaptationRules.g:437:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getOrConditionAccess().getLogicalConditionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalAdaptationRules.g:443:4: ( (lv_operator_2_0= ruleOr ) )
            	    // InternalAdaptationRules.g:444:5: (lv_operator_2_0= ruleOr )
            	    {
            	    // InternalAdaptationRules.g:444:5: (lv_operator_2_0= ruleOr )
            	    // InternalAdaptationRules.g:445:6: lv_operator_2_0= ruleOr
            	    {

            	    						newCompositeNode(grammarAccess.getOrConditionAccess().getOperatorOrEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_operator_2_0=ruleOr();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOrConditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"operator",
            	    							lv_operator_2_0,
            	    							"com.project.mde.adaptation.dsl.AdaptationRules.Or");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalAdaptationRules.g:462:4: ( (lv_right_3_0= ruleAndCondition ) )
            	    // InternalAdaptationRules.g:463:5: (lv_right_3_0= ruleAndCondition )
            	    {
            	    // InternalAdaptationRules.g:463:5: (lv_right_3_0= ruleAndCondition )
            	    // InternalAdaptationRules.g:464:6: lv_right_3_0= ruleAndCondition
            	    {

            	    						newCompositeNode(grammarAccess.getOrConditionAccess().getRightAndConditionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_17);
            	    lv_right_3_0=ruleAndCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOrConditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.project.mde.adaptation.dsl.AdaptationRules.AndCondition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrCondition"


    // $ANTLR start "entryRuleAndCondition"
    // InternalAdaptationRules.g:486:1: entryRuleAndCondition returns [EObject current=null] : iv_ruleAndCondition= ruleAndCondition EOF ;
    public final EObject entryRuleAndCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndCondition = null;


        try {
            // InternalAdaptationRules.g:486:53: (iv_ruleAndCondition= ruleAndCondition EOF )
            // InternalAdaptationRules.g:487:2: iv_ruleAndCondition= ruleAndCondition EOF
            {
             newCompositeNode(grammarAccess.getAndConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAndCondition=ruleAndCondition();

            state._fsp--;

             current =iv_ruleAndCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndCondition"


    // $ANTLR start "ruleAndCondition"
    // InternalAdaptationRules.g:493:1: ruleAndCondition returns [EObject current=null] : (this_UnaryCondition_0= ruleUnaryCondition ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )* ) ;
    public final EObject ruleAndCondition() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryCondition_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:499:2: ( (this_UnaryCondition_0= ruleUnaryCondition ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )* ) )
            // InternalAdaptationRules.g:500:2: (this_UnaryCondition_0= ruleUnaryCondition ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )* )
            {
            // InternalAdaptationRules.g:500:2: (this_UnaryCondition_0= ruleUnaryCondition ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )* )
            // InternalAdaptationRules.g:501:3: this_UnaryCondition_0= ruleUnaryCondition ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndConditionAccess().getUnaryConditionParserRuleCall_0());
            		
            pushFollow(FOLLOW_19);
            this_UnaryCondition_0=ruleUnaryCondition();

            state._fsp--;


            			current = this_UnaryCondition_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalAdaptationRules.g:509:3: ( () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==32) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalAdaptationRules.g:510:4: () ( (lv_operator_2_0= ruleAnd ) ) ( (lv_right_3_0= ruleUnaryCondition ) )
            	    {
            	    // InternalAdaptationRules.g:510:4: ()
            	    // InternalAdaptationRules.g:511:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndConditionAccess().getLogicalConditionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalAdaptationRules.g:517:4: ( (lv_operator_2_0= ruleAnd ) )
            	    // InternalAdaptationRules.g:518:5: (lv_operator_2_0= ruleAnd )
            	    {
            	    // InternalAdaptationRules.g:518:5: (lv_operator_2_0= ruleAnd )
            	    // InternalAdaptationRules.g:519:6: lv_operator_2_0= ruleAnd
            	    {

            	    						newCompositeNode(grammarAccess.getAndConditionAccess().getOperatorAndEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_operator_2_0=ruleAnd();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndConditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"operator",
            	    							lv_operator_2_0,
            	    							"com.project.mde.adaptation.dsl.AdaptationRules.And");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalAdaptationRules.g:536:4: ( (lv_right_3_0= ruleUnaryCondition ) )
            	    // InternalAdaptationRules.g:537:5: (lv_right_3_0= ruleUnaryCondition )
            	    {
            	    // InternalAdaptationRules.g:537:5: (lv_right_3_0= ruleUnaryCondition )
            	    // InternalAdaptationRules.g:538:6: lv_right_3_0= ruleUnaryCondition
            	    {

            	    						newCompositeNode(grammarAccess.getAndConditionAccess().getRightUnaryConditionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_19);
            	    lv_right_3_0=ruleUnaryCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndConditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.project.mde.adaptation.dsl.AdaptationRules.UnaryCondition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndCondition"


    // $ANTLR start "entryRuleUnaryCondition"
    // InternalAdaptationRules.g:560:1: entryRuleUnaryCondition returns [EObject current=null] : iv_ruleUnaryCondition= ruleUnaryCondition EOF ;
    public final EObject entryRuleUnaryCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryCondition = null;


        try {
            // InternalAdaptationRules.g:560:55: (iv_ruleUnaryCondition= ruleUnaryCondition EOF )
            // InternalAdaptationRules.g:561:2: iv_ruleUnaryCondition= ruleUnaryCondition EOF
            {
             newCompositeNode(grammarAccess.getUnaryConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnaryCondition=ruleUnaryCondition();

            state._fsp--;

             current =iv_ruleUnaryCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryCondition"


    // $ANTLR start "ruleUnaryCondition"
    // InternalAdaptationRules.g:567:1: ruleUnaryCondition returns [EObject current=null] : ( ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) ) | (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' ) | this_ComparisonCondition_6= ruleComparisonCondition ) ;
    public final EObject ruleUnaryCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_operand_2_0 = null;

        EObject this_Condition_4 = null;

        EObject this_ComparisonCondition_6 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:573:2: ( ( ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) ) | (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' ) | this_ComparisonCondition_6= ruleComparisonCondition ) )
            // InternalAdaptationRules.g:574:2: ( ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) ) | (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' ) | this_ComparisonCondition_6= ruleComparisonCondition )
            {
            // InternalAdaptationRules.g:574:2: ( ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) ) | (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' ) | this_ComparisonCondition_6= ruleComparisonCondition )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt7=1;
                }
                break;
            case 21:
                {
                alt7=2;
                }
                break;
            case RULE_ID:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalAdaptationRules.g:575:3: ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) )
                    {
                    // InternalAdaptationRules.g:575:3: ( () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) ) )
                    // InternalAdaptationRules.g:576:4: () otherlv_1= 'not' ( (lv_operand_2_0= ruleUnaryCondition ) )
                    {
                    // InternalAdaptationRules.g:576:4: ()
                    // InternalAdaptationRules.g:577:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getUnaryConditionAccess().getNotConditionAction_0_0(),
                    						current);
                    				

                    }

                    otherlv_1=(Token)match(input,20,FOLLOW_18); 

                    				newLeafNode(otherlv_1, grammarAccess.getUnaryConditionAccess().getNotKeyword_0_1());
                    			
                    // InternalAdaptationRules.g:587:4: ( (lv_operand_2_0= ruleUnaryCondition ) )
                    // InternalAdaptationRules.g:588:5: (lv_operand_2_0= ruleUnaryCondition )
                    {
                    // InternalAdaptationRules.g:588:5: (lv_operand_2_0= ruleUnaryCondition )
                    // InternalAdaptationRules.g:589:6: lv_operand_2_0= ruleUnaryCondition
                    {

                    						newCompositeNode(grammarAccess.getUnaryConditionAccess().getOperandUnaryConditionParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operand_2_0=ruleUnaryCondition();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryConditionRule());
                    						}
                    						set(
                    							current,
                    							"operand",
                    							lv_operand_2_0,
                    							"com.project.mde.adaptation.dsl.AdaptationRules.UnaryCondition");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:608:3: (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' )
                    {
                    // InternalAdaptationRules.g:608:3: (otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')' )
                    // InternalAdaptationRules.g:609:4: otherlv_3= '(' this_Condition_4= ruleCondition otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,21,FOLLOW_18); 

                    				newLeafNode(otherlv_3, grammarAccess.getUnaryConditionAccess().getLeftParenthesisKeyword_1_0());
                    			

                    				newCompositeNode(grammarAccess.getUnaryConditionAccess().getConditionParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_20);
                    this_Condition_4=ruleCondition();

                    state._fsp--;


                    				current = this_Condition_4;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_5=(Token)match(input,22,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getUnaryConditionAccess().getRightParenthesisKeyword_1_2());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalAdaptationRules.g:627:3: this_ComparisonCondition_6= ruleComparisonCondition
                    {

                    			newCompositeNode(grammarAccess.getUnaryConditionAccess().getComparisonConditionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComparisonCondition_6=ruleComparisonCondition();

                    state._fsp--;


                    			current = this_ComparisonCondition_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryCondition"


    // $ANTLR start "entryRuleComparisonCondition"
    // InternalAdaptationRules.g:639:1: entryRuleComparisonCondition returns [EObject current=null] : iv_ruleComparisonCondition= ruleComparisonCondition EOF ;
    public final EObject entryRuleComparisonCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparisonCondition = null;


        try {
            // InternalAdaptationRules.g:639:60: (iv_ruleComparisonCondition= ruleComparisonCondition EOF )
            // InternalAdaptationRules.g:640:2: iv_ruleComparisonCondition= ruleComparisonCondition EOF
            {
             newCompositeNode(grammarAccess.getComparisonConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComparisonCondition=ruleComparisonCondition();

            state._fsp--;

             current =iv_ruleComparisonCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComparisonCondition"


    // $ANTLR start "ruleComparisonCondition"
    // InternalAdaptationRules.g:646:1: ruleComparisonCondition returns [EObject current=null] : ( ( (lv_attribute_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleComparisonOperator ) ) ( (lv_value_2_0= ruleValue ) ) ) ;
    public final EObject ruleComparisonCondition() throws RecognitionException {
        EObject current = null;

        Token lv_attribute_0_0=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:652:2: ( ( ( (lv_attribute_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleComparisonOperator ) ) ( (lv_value_2_0= ruleValue ) ) ) )
            // InternalAdaptationRules.g:653:2: ( ( (lv_attribute_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleComparisonOperator ) ) ( (lv_value_2_0= ruleValue ) ) )
            {
            // InternalAdaptationRules.g:653:2: ( ( (lv_attribute_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleComparisonOperator ) ) ( (lv_value_2_0= ruleValue ) ) )
            // InternalAdaptationRules.g:654:3: ( (lv_attribute_0_0= RULE_ID ) ) ( (lv_operator_1_0= ruleComparisonOperator ) ) ( (lv_value_2_0= ruleValue ) )
            {
            // InternalAdaptationRules.g:654:3: ( (lv_attribute_0_0= RULE_ID ) )
            // InternalAdaptationRules.g:655:4: (lv_attribute_0_0= RULE_ID )
            {
            // InternalAdaptationRules.g:655:4: (lv_attribute_0_0= RULE_ID )
            // InternalAdaptationRules.g:656:5: lv_attribute_0_0= RULE_ID
            {
            lv_attribute_0_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(lv_attribute_0_0, grammarAccess.getComparisonConditionAccess().getAttributeIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComparisonConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"attribute",
            						lv_attribute_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalAdaptationRules.g:672:3: ( (lv_operator_1_0= ruleComparisonOperator ) )
            // InternalAdaptationRules.g:673:4: (lv_operator_1_0= ruleComparisonOperator )
            {
            // InternalAdaptationRules.g:673:4: (lv_operator_1_0= ruleComparisonOperator )
            // InternalAdaptationRules.g:674:5: lv_operator_1_0= ruleComparisonOperator
            {

            					newCompositeNode(grammarAccess.getComparisonConditionAccess().getOperatorComparisonOperatorEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_22);
            lv_operator_1_0=ruleComparisonOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComparisonConditionRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_1_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.ComparisonOperator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalAdaptationRules.g:691:3: ( (lv_value_2_0= ruleValue ) )
            // InternalAdaptationRules.g:692:4: (lv_value_2_0= ruleValue )
            {
            // InternalAdaptationRules.g:692:4: (lv_value_2_0= ruleValue )
            // InternalAdaptationRules.g:693:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getComparisonConditionAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComparisonConditionRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparisonCondition"


    // $ANTLR start "entryRuleValue"
    // InternalAdaptationRules.g:714:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalAdaptationRules.g:714:46: (iv_ruleValue= ruleValue EOF )
            // InternalAdaptationRules.g:715:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalAdaptationRules.g:721:1: ruleValue returns [EObject current=null] : (this_StringValue_0= ruleStringValue | this_DecimalValue_1= ruleDecimalValue | this_IntegerValue_2= ruleIntegerValue | this_BooleanValue_3= ruleBooleanValue ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_StringValue_0 = null;

        EObject this_DecimalValue_1 = null;

        EObject this_IntegerValue_2 = null;

        EObject this_BooleanValue_3 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:727:2: ( (this_StringValue_0= ruleStringValue | this_DecimalValue_1= ruleDecimalValue | this_IntegerValue_2= ruleIntegerValue | this_BooleanValue_3= ruleBooleanValue ) )
            // InternalAdaptationRules.g:728:2: (this_StringValue_0= ruleStringValue | this_DecimalValue_1= ruleDecimalValue | this_IntegerValue_2= ruleIntegerValue | this_BooleanValue_3= ruleBooleanValue )
            {
            // InternalAdaptationRules.g:728:2: (this_StringValue_0= ruleStringValue | this_DecimalValue_1= ruleDecimalValue | this_IntegerValue_2= ruleIntegerValue | this_BooleanValue_3= ruleBooleanValue )
            int alt8=4;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt8=1;
                }
                break;
            case 23:
                {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==RULE_INT) ) {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==24) ) {
                        alt8=2;
                    }
                    else if ( (LA8_3==EOF||LA8_3==18||LA8_3==22||(LA8_3>=31 && LA8_3<=32)) ) {
                        alt8=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                int LA8_3 = input.LA(2);

                if ( (LA8_3==24) ) {
                    alt8=2;
                }
                else if ( (LA8_3==EOF||LA8_3==18||LA8_3==22||(LA8_3>=31 && LA8_3<=32)) ) {
                    alt8=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 3, input);

                    throw nvae;
                }
                }
                break;
            case 25:
            case 26:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalAdaptationRules.g:729:3: this_StringValue_0= ruleStringValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getStringValueParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringValue_0=ruleStringValue();

                    state._fsp--;


                    			current = this_StringValue_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:738:3: this_DecimalValue_1= ruleDecimalValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getDecimalValueParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_DecimalValue_1=ruleDecimalValue();

                    state._fsp--;


                    			current = this_DecimalValue_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalAdaptationRules.g:747:3: this_IntegerValue_2= ruleIntegerValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getIntegerValueParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntegerValue_2=ruleIntegerValue();

                    state._fsp--;


                    			current = this_IntegerValue_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalAdaptationRules.g:756:3: this_BooleanValue_3= ruleBooleanValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_BooleanValue_3=ruleBooleanValue();

                    state._fsp--;


                    			current = this_BooleanValue_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleStringValue"
    // InternalAdaptationRules.g:768:1: entryRuleStringValue returns [EObject current=null] : iv_ruleStringValue= ruleStringValue EOF ;
    public final EObject entryRuleStringValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringValue = null;


        try {
            // InternalAdaptationRules.g:768:52: (iv_ruleStringValue= ruleStringValue EOF )
            // InternalAdaptationRules.g:769:2: iv_ruleStringValue= ruleStringValue EOF
            {
             newCompositeNode(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringValue=ruleStringValue();

            state._fsp--;

             current =iv_ruleStringValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalAdaptationRules.g:775:1: ruleStringValue returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:781:2: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // InternalAdaptationRules.g:782:2: ( (lv_value_0_0= RULE_STRING ) )
            {
            // InternalAdaptationRules.g:782:2: ( (lv_value_0_0= RULE_STRING ) )
            // InternalAdaptationRules.g:783:3: (lv_value_0_0= RULE_STRING )
            {
            // InternalAdaptationRules.g:783:3: (lv_value_0_0= RULE_STRING )
            // InternalAdaptationRules.g:784:4: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getStringValueRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"org.eclipse.xtext.common.Terminals.STRING");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleDecimalValue"
    // InternalAdaptationRules.g:803:1: entryRuleDecimalValue returns [EObject current=null] : iv_ruleDecimalValue= ruleDecimalValue EOF ;
    public final EObject entryRuleDecimalValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecimalValue = null;


        try {
            // InternalAdaptationRules.g:803:53: (iv_ruleDecimalValue= ruleDecimalValue EOF )
            // InternalAdaptationRules.g:804:2: iv_ruleDecimalValue= ruleDecimalValue EOF
            {
             newCompositeNode(grammarAccess.getDecimalValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDecimalValue=ruleDecimalValue();

            state._fsp--;

             current =iv_ruleDecimalValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDecimalValue"


    // $ANTLR start "ruleDecimalValue"
    // InternalAdaptationRules.g:810:1: ruleDecimalValue returns [EObject current=null] : ( (lv_value_0_0= ruleDecimal ) ) ;
    public final EObject ruleDecimalValue() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:816:2: ( ( (lv_value_0_0= ruleDecimal ) ) )
            // InternalAdaptationRules.g:817:2: ( (lv_value_0_0= ruleDecimal ) )
            {
            // InternalAdaptationRules.g:817:2: ( (lv_value_0_0= ruleDecimal ) )
            // InternalAdaptationRules.g:818:3: (lv_value_0_0= ruleDecimal )
            {
            // InternalAdaptationRules.g:818:3: (lv_value_0_0= ruleDecimal )
            // InternalAdaptationRules.g:819:4: lv_value_0_0= ruleDecimal
            {

            				newCompositeNode(grammarAccess.getDecimalValueAccess().getValueDecimalParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleDecimal();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getDecimalValueRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"com.project.mde.adaptation.dsl.AdaptationRules.Decimal");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDecimalValue"


    // $ANTLR start "entryRuleIntegerValue"
    // InternalAdaptationRules.g:839:1: entryRuleIntegerValue returns [EObject current=null] : iv_ruleIntegerValue= ruleIntegerValue EOF ;
    public final EObject entryRuleIntegerValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerValue = null;


        try {
            // InternalAdaptationRules.g:839:53: (iv_ruleIntegerValue= ruleIntegerValue EOF )
            // InternalAdaptationRules.g:840:2: iv_ruleIntegerValue= ruleIntegerValue EOF
            {
             newCompositeNode(grammarAccess.getIntegerValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerValue=ruleIntegerValue();

            state._fsp--;

             current =iv_ruleIntegerValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerValue"


    // $ANTLR start "ruleIntegerValue"
    // InternalAdaptationRules.g:846:1: ruleIntegerValue returns [EObject current=null] : ( (lv_value_0_0= ruleLong ) ) ;
    public final EObject ruleIntegerValue() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:852:2: ( ( (lv_value_0_0= ruleLong ) ) )
            // InternalAdaptationRules.g:853:2: ( (lv_value_0_0= ruleLong ) )
            {
            // InternalAdaptationRules.g:853:2: ( (lv_value_0_0= ruleLong ) )
            // InternalAdaptationRules.g:854:3: (lv_value_0_0= ruleLong )
            {
            // InternalAdaptationRules.g:854:3: (lv_value_0_0= ruleLong )
            // InternalAdaptationRules.g:855:4: lv_value_0_0= ruleLong
            {

            				newCompositeNode(grammarAccess.getIntegerValueAccess().getValueLongParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleLong();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getIntegerValueRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"com.project.mde.adaptation.dsl.AdaptationRules.Long");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalAdaptationRules.g:875:1: entryRuleBooleanValue returns [EObject current=null] : iv_ruleBooleanValue= ruleBooleanValue EOF ;
    public final EObject entryRuleBooleanValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanValue = null;


        try {
            // InternalAdaptationRules.g:875:53: (iv_ruleBooleanValue= ruleBooleanValue EOF )
            // InternalAdaptationRules.g:876:2: iv_ruleBooleanValue= ruleBooleanValue EOF
            {
             newCompositeNode(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanValue=ruleBooleanValue();

            state._fsp--;

             current =iv_ruleBooleanValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalAdaptationRules.g:882:1: ruleBooleanValue returns [EObject current=null] : ( (lv_value_0_0= ruleBoolean ) ) ;
    public final EObject ruleBooleanValue() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:888:2: ( ( (lv_value_0_0= ruleBoolean ) ) )
            // InternalAdaptationRules.g:889:2: ( (lv_value_0_0= ruleBoolean ) )
            {
            // InternalAdaptationRules.g:889:2: ( (lv_value_0_0= ruleBoolean ) )
            // InternalAdaptationRules.g:890:3: (lv_value_0_0= ruleBoolean )
            {
            // InternalAdaptationRules.g:890:3: (lv_value_0_0= ruleBoolean )
            // InternalAdaptationRules.g:891:4: lv_value_0_0= ruleBoolean
            {

            				newCompositeNode(grammarAccess.getBooleanValueAccess().getValueBooleanParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleBoolean();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getBooleanValueRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"com.project.mde.adaptation.dsl.AdaptationRules.Boolean");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "entryRuleSignedInt"
    // InternalAdaptationRules.g:911:1: entryRuleSignedInt returns [String current=null] : iv_ruleSignedInt= ruleSignedInt EOF ;
    public final String entryRuleSignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSignedInt = null;


        try {
            // InternalAdaptationRules.g:911:49: (iv_ruleSignedInt= ruleSignedInt EOF )
            // InternalAdaptationRules.g:912:2: iv_ruleSignedInt= ruleSignedInt EOF
            {
             newCompositeNode(grammarAccess.getSignedIntRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSignedInt=ruleSignedInt();

            state._fsp--;

             current =iv_ruleSignedInt.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSignedInt"


    // $ANTLR start "ruleSignedInt"
    // InternalAdaptationRules.g:918:1: ruleSignedInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleSignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:924:2: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // InternalAdaptationRules.g:925:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // InternalAdaptationRules.g:925:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // InternalAdaptationRules.g:926:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // InternalAdaptationRules.g:926:3: (kw= '-' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalAdaptationRules.g:927:4: kw= '-'
                    {
                    kw=(Token)match(input,23,FOLLOW_23); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getSignedIntAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getSignedIntAccess().getINTTerminalRuleCall_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSignedInt"


    // $ANTLR start "entryRuleLong"
    // InternalAdaptationRules.g:944:1: entryRuleLong returns [String current=null] : iv_ruleLong= ruleLong EOF ;
    public final String entryRuleLong() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLong = null;


        try {
            // InternalAdaptationRules.g:944:44: (iv_ruleLong= ruleLong EOF )
            // InternalAdaptationRules.g:945:2: iv_ruleLong= ruleLong EOF
            {
             newCompositeNode(grammarAccess.getLongRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLong=ruleLong();

            state._fsp--;

             current =iv_ruleLong.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLong"


    // $ANTLR start "ruleLong"
    // InternalAdaptationRules.g:951:1: ruleLong returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleLong() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:957:2: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // InternalAdaptationRules.g:958:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // InternalAdaptationRules.g:958:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // InternalAdaptationRules.g:959:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // InternalAdaptationRules.g:959:3: (kw= '-' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalAdaptationRules.g:960:4: kw= '-'
                    {
                    kw=(Token)match(input,23,FOLLOW_23); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getLongAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getLongAccess().getINTTerminalRuleCall_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLong"


    // $ANTLR start "entryRuleDecimal"
    // InternalAdaptationRules.g:977:1: entryRuleDecimal returns [String current=null] : iv_ruleDecimal= ruleDecimal EOF ;
    public final String entryRuleDecimal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDecimal = null;


        try {
            // InternalAdaptationRules.g:977:47: (iv_ruleDecimal= ruleDecimal EOF )
            // InternalAdaptationRules.g:978:2: iv_ruleDecimal= ruleDecimal EOF
            {
             newCompositeNode(grammarAccess.getDecimalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDecimal=ruleDecimal();

            state._fsp--;

             current =iv_ruleDecimal.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDecimal"


    // $ANTLR start "ruleDecimal"
    // InternalAdaptationRules.g:984:1: ruleDecimal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT kw= '.' this_INT_3= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleDecimal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:990:2: ( ( (kw= '-' )? this_INT_1= RULE_INT kw= '.' this_INT_3= RULE_INT ) )
            // InternalAdaptationRules.g:991:2: ( (kw= '-' )? this_INT_1= RULE_INT kw= '.' this_INT_3= RULE_INT )
            {
            // InternalAdaptationRules.g:991:2: ( (kw= '-' )? this_INT_1= RULE_INT kw= '.' this_INT_3= RULE_INT )
            // InternalAdaptationRules.g:992:3: (kw= '-' )? this_INT_1= RULE_INT kw= '.' this_INT_3= RULE_INT
            {
            // InternalAdaptationRules.g:992:3: (kw= '-' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalAdaptationRules.g:993:4: kw= '-'
                    {
                    kw=(Token)match(input,23,FOLLOW_23); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getDecimalAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_24); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getDecimalAccess().getINTTerminalRuleCall_1());
            		
            kw=(Token)match(input,24,FOLLOW_23); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDecimalAccess().getFullStopKeyword_2());
            		
            this_INT_3=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_3);
            		

            			newLeafNode(this_INT_3, grammarAccess.getDecimalAccess().getINTTerminalRuleCall_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDecimal"


    // $ANTLR start "entryRuleBoolean"
    // InternalAdaptationRules.g:1022:1: entryRuleBoolean returns [String current=null] : iv_ruleBoolean= ruleBoolean EOF ;
    public final String entryRuleBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBoolean = null;


        try {
            // InternalAdaptationRules.g:1022:47: (iv_ruleBoolean= ruleBoolean EOF )
            // InternalAdaptationRules.g:1023:2: iv_ruleBoolean= ruleBoolean EOF
            {
             newCompositeNode(grammarAccess.getBooleanRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBoolean=ruleBoolean();

            state._fsp--;

             current =iv_ruleBoolean.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoolean"


    // $ANTLR start "ruleBoolean"
    // InternalAdaptationRules.g:1029:1: ruleBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1035:2: ( (kw= 'true' | kw= 'false' ) )
            // InternalAdaptationRules.g:1036:2: (kw= 'true' | kw= 'false' )
            {
            // InternalAdaptationRules.g:1036:2: (kw= 'true' | kw= 'false' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==25) ) {
                alt12=1;
            }
            else if ( (LA12_0==26) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalAdaptationRules.g:1037:3: kw= 'true'
                    {
                    kw=(Token)match(input,25,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanAccess().getTrueKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1043:3: kw= 'false'
                    {
                    kw=(Token)match(input,26,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanAccess().getFalseKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "entryRuleAction"
    // InternalAdaptationRules.g:1052:1: entryRuleAction returns [EObject current=null] : iv_ruleAction= ruleAction EOF ;
    public final EObject entryRuleAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAction = null;


        try {
            // InternalAdaptationRules.g:1052:47: (iv_ruleAction= ruleAction EOF )
            // InternalAdaptationRules.g:1053:2: iv_ruleAction= ruleAction EOF
            {
             newCompositeNode(grammarAccess.getActionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAction=ruleAction();

            state._fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAction"


    // $ANTLR start "ruleAction"
    // InternalAdaptationRules.g:1059:1: ruleAction returns [EObject current=null] : (otherlv_0= 'action' ( (lv_type_1_0= ruleActionType ) ) ( (lv_parameters_2_0= ruleAdaptationParameters ) )? ) ;
    public final EObject ruleAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Enumerator lv_type_1_0 = null;

        EObject lv_parameters_2_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:1065:2: ( (otherlv_0= 'action' ( (lv_type_1_0= ruleActionType ) ) ( (lv_parameters_2_0= ruleAdaptationParameters ) )? ) )
            // InternalAdaptationRules.g:1066:2: (otherlv_0= 'action' ( (lv_type_1_0= ruleActionType ) ) ( (lv_parameters_2_0= ruleAdaptationParameters ) )? )
            {
            // InternalAdaptationRules.g:1066:2: (otherlv_0= 'action' ( (lv_type_1_0= ruleActionType ) ) ( (lv_parameters_2_0= ruleAdaptationParameters ) )? )
            // InternalAdaptationRules.g:1067:3: otherlv_0= 'action' ( (lv_type_1_0= ruleActionType ) ) ( (lv_parameters_2_0= ruleAdaptationParameters ) )?
            {
            otherlv_0=(Token)match(input,27,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getActionAccess().getActionKeyword_0());
            		
            // InternalAdaptationRules.g:1071:3: ( (lv_type_1_0= ruleActionType ) )
            // InternalAdaptationRules.g:1072:4: (lv_type_1_0= ruleActionType )
            {
            // InternalAdaptationRules.g:1072:4: (lv_type_1_0= ruleActionType )
            // InternalAdaptationRules.g:1073:5: lv_type_1_0= ruleActionType
            {

            					newCompositeNode(grammarAccess.getActionAccess().getTypeActionTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_26);
            lv_type_1_0=ruleActionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getActionRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"com.project.mde.adaptation.dsl.AdaptationRules.ActionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalAdaptationRules.g:1090:3: ( (lv_parameters_2_0= ruleAdaptationParameters ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=28 && LA13_0<=29)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalAdaptationRules.g:1091:4: (lv_parameters_2_0= ruleAdaptationParameters )
                    {
                    // InternalAdaptationRules.g:1091:4: (lv_parameters_2_0= ruleAdaptationParameters )
                    // InternalAdaptationRules.g:1092:5: lv_parameters_2_0= ruleAdaptationParameters
                    {

                    					newCompositeNode(grammarAccess.getActionAccess().getParametersAdaptationParametersParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_2_0=ruleAdaptationParameters();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getActionRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_2_0,
                    						"com.project.mde.adaptation.dsl.AdaptationRules.AdaptationParameters");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "entryRuleAdaptationParameters"
    // InternalAdaptationRules.g:1113:1: entryRuleAdaptationParameters returns [EObject current=null] : iv_ruleAdaptationParameters= ruleAdaptationParameters EOF ;
    public final EObject entryRuleAdaptationParameters() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdaptationParameters = null;


        try {
            // InternalAdaptationRules.g:1113:61: (iv_ruleAdaptationParameters= ruleAdaptationParameters EOF )
            // InternalAdaptationRules.g:1114:2: iv_ruleAdaptationParameters= ruleAdaptationParameters EOF
            {
             newCompositeNode(grammarAccess.getAdaptationParametersRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdaptationParameters=ruleAdaptationParameters();

            state._fsp--;

             current =iv_ruleAdaptationParameters; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdaptationParameters"


    // $ANTLR start "ruleAdaptationParameters"
    // InternalAdaptationRules.g:1120:1: ruleAdaptationParameters returns [EObject current=null] : ( (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? ) | (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? ) ) ;
    public final EObject ruleAdaptationParameters() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Enumerator lv_hintLevel_1_0 = null;

        Enumerator lv_feedbackStyle_3_0 = null;

        Enumerator lv_feedbackStyle_5_0 = null;

        Enumerator lv_hintLevel_7_0 = null;



        	enterRule();

        try {
            // InternalAdaptationRules.g:1126:2: ( ( (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? ) | (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? ) ) )
            // InternalAdaptationRules.g:1127:2: ( (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? ) | (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? ) )
            {
            // InternalAdaptationRules.g:1127:2: ( (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? ) | (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==28) ) {
                alt16=1;
            }
            else if ( (LA16_0==29) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalAdaptationRules.g:1128:3: (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? )
                    {
                    // InternalAdaptationRules.g:1128:3: (otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )? )
                    // InternalAdaptationRules.g:1129:4: otherlv_0= 'level' ( (lv_hintLevel_1_0= ruleHintLevel ) ) (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )?
                    {
                    otherlv_0=(Token)match(input,28,FOLLOW_27); 

                    				newLeafNode(otherlv_0, grammarAccess.getAdaptationParametersAccess().getLevelKeyword_0_0());
                    			
                    // InternalAdaptationRules.g:1133:4: ( (lv_hintLevel_1_0= ruleHintLevel ) )
                    // InternalAdaptationRules.g:1134:5: (lv_hintLevel_1_0= ruleHintLevel )
                    {
                    // InternalAdaptationRules.g:1134:5: (lv_hintLevel_1_0= ruleHintLevel )
                    // InternalAdaptationRules.g:1135:6: lv_hintLevel_1_0= ruleHintLevel
                    {

                    						newCompositeNode(grammarAccess.getAdaptationParametersAccess().getHintLevelHintLevelEnumRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_28);
                    lv_hintLevel_1_0=ruleHintLevel();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAdaptationParametersRule());
                    						}
                    						set(
                    							current,
                    							"hintLevel",
                    							lv_hintLevel_1_0,
                    							"com.project.mde.adaptation.dsl.AdaptationRules.HintLevel");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalAdaptationRules.g:1152:4: (otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==29) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalAdaptationRules.g:1153:5: otherlv_2= 'style' ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) )
                            {
                            otherlv_2=(Token)match(input,29,FOLLOW_29); 

                            					newLeafNode(otherlv_2, grammarAccess.getAdaptationParametersAccess().getStyleKeyword_0_2_0());
                            				
                            // InternalAdaptationRules.g:1157:5: ( (lv_feedbackStyle_3_0= ruleFeedbackStyle ) )
                            // InternalAdaptationRules.g:1158:6: (lv_feedbackStyle_3_0= ruleFeedbackStyle )
                            {
                            // InternalAdaptationRules.g:1158:6: (lv_feedbackStyle_3_0= ruleFeedbackStyle )
                            // InternalAdaptationRules.g:1159:7: lv_feedbackStyle_3_0= ruleFeedbackStyle
                            {

                            							newCompositeNode(grammarAccess.getAdaptationParametersAccess().getFeedbackStyleFeedbackStyleEnumRuleCall_0_2_1_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_feedbackStyle_3_0=ruleFeedbackStyle();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getAdaptationParametersRule());
                            							}
                            							set(
                            								current,
                            								"feedbackStyle",
                            								lv_feedbackStyle_3_0,
                            								"com.project.mde.adaptation.dsl.AdaptationRules.FeedbackStyle");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1179:3: (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? )
                    {
                    // InternalAdaptationRules.g:1179:3: (otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )? )
                    // InternalAdaptationRules.g:1180:4: otherlv_4= 'style' ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) ) (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )?
                    {
                    otherlv_4=(Token)match(input,29,FOLLOW_29); 

                    				newLeafNode(otherlv_4, grammarAccess.getAdaptationParametersAccess().getStyleKeyword_1_0());
                    			
                    // InternalAdaptationRules.g:1184:4: ( (lv_feedbackStyle_5_0= ruleFeedbackStyle ) )
                    // InternalAdaptationRules.g:1185:5: (lv_feedbackStyle_5_0= ruleFeedbackStyle )
                    {
                    // InternalAdaptationRules.g:1185:5: (lv_feedbackStyle_5_0= ruleFeedbackStyle )
                    // InternalAdaptationRules.g:1186:6: lv_feedbackStyle_5_0= ruleFeedbackStyle
                    {

                    						newCompositeNode(grammarAccess.getAdaptationParametersAccess().getFeedbackStyleFeedbackStyleEnumRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_feedbackStyle_5_0=ruleFeedbackStyle();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAdaptationParametersRule());
                    						}
                    						set(
                    							current,
                    							"feedbackStyle",
                    							lv_feedbackStyle_5_0,
                    							"com.project.mde.adaptation.dsl.AdaptationRules.FeedbackStyle");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalAdaptationRules.g:1203:4: (otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) ) )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==28) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalAdaptationRules.g:1204:5: otherlv_6= 'level' ( (lv_hintLevel_7_0= ruleHintLevel ) )
                            {
                            otherlv_6=(Token)match(input,28,FOLLOW_27); 

                            					newLeafNode(otherlv_6, grammarAccess.getAdaptationParametersAccess().getLevelKeyword_1_2_0());
                            				
                            // InternalAdaptationRules.g:1208:5: ( (lv_hintLevel_7_0= ruleHintLevel ) )
                            // InternalAdaptationRules.g:1209:6: (lv_hintLevel_7_0= ruleHintLevel )
                            {
                            // InternalAdaptationRules.g:1209:6: (lv_hintLevel_7_0= ruleHintLevel )
                            // InternalAdaptationRules.g:1210:7: lv_hintLevel_7_0= ruleHintLevel
                            {

                            							newCompositeNode(grammarAccess.getAdaptationParametersAccess().getHintLevelHintLevelEnumRuleCall_1_2_1_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_hintLevel_7_0=ruleHintLevel();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getAdaptationParametersRule());
                            							}
                            							set(
                            								current,
                            								"hintLevel",
                            								lv_hintLevel_7_0,
                            								"com.project.mde.adaptation.dsl.AdaptationRules.HintLevel");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdaptationParameters"


    // $ANTLR start "ruleEventType"
    // InternalAdaptationRules.g:1233:1: ruleEventType returns [Enumerator current=null] : (enumLiteral_0= 'ATTEMPT_EVALUATED' ) ;
    public final Enumerator ruleEventType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1239:2: ( (enumLiteral_0= 'ATTEMPT_EVALUATED' ) )
            // InternalAdaptationRules.g:1240:2: (enumLiteral_0= 'ATTEMPT_EVALUATED' )
            {
            // InternalAdaptationRules.g:1240:2: (enumLiteral_0= 'ATTEMPT_EVALUATED' )
            // InternalAdaptationRules.g:1241:3: enumLiteral_0= 'ATTEMPT_EVALUATED'
            {
            enumLiteral_0=(Token)match(input,30,FOLLOW_2); 

            			current = grammarAccess.getEventTypeAccess().getATTEMPT_EVALUATEDEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getEventTypeAccess().getATTEMPT_EVALUATEDEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEventType"


    // $ANTLR start "ruleOr"
    // InternalAdaptationRules.g:1250:1: ruleOr returns [Enumerator current=null] : (enumLiteral_0= 'or' ) ;
    public final Enumerator ruleOr() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1256:2: ( (enumLiteral_0= 'or' ) )
            // InternalAdaptationRules.g:1257:2: (enumLiteral_0= 'or' )
            {
            // InternalAdaptationRules.g:1257:2: (enumLiteral_0= 'or' )
            // InternalAdaptationRules.g:1258:3: enumLiteral_0= 'or'
            {
            enumLiteral_0=(Token)match(input,31,FOLLOW_2); 

            			current = grammarAccess.getOrAccess().getOREnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOrAccess().getOREnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOr"


    // $ANTLR start "ruleAnd"
    // InternalAdaptationRules.g:1267:1: ruleAnd returns [Enumerator current=null] : (enumLiteral_0= 'and' ) ;
    public final Enumerator ruleAnd() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1273:2: ( (enumLiteral_0= 'and' ) )
            // InternalAdaptationRules.g:1274:2: (enumLiteral_0= 'and' )
            {
            // InternalAdaptationRules.g:1274:2: (enumLiteral_0= 'and' )
            // InternalAdaptationRules.g:1275:3: enumLiteral_0= 'and'
            {
            enumLiteral_0=(Token)match(input,32,FOLLOW_2); 

            			current = grammarAccess.getAndAccess().getANDEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getAndAccess().getANDEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "ruleComparisonOperator"
    // InternalAdaptationRules.g:1284:1: ruleComparisonOperator returns [Enumerator current=null] : ( (enumLiteral_0= '==' ) | (enumLiteral_1= '!=' ) | (enumLiteral_2= '>' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '<' ) | (enumLiteral_5= '<=' ) | (enumLiteral_6= 'contains' ) ) ;
    public final Enumerator ruleComparisonOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1290:2: ( ( (enumLiteral_0= '==' ) | (enumLiteral_1= '!=' ) | (enumLiteral_2= '>' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '<' ) | (enumLiteral_5= '<=' ) | (enumLiteral_6= 'contains' ) ) )
            // InternalAdaptationRules.g:1291:2: ( (enumLiteral_0= '==' ) | (enumLiteral_1= '!=' ) | (enumLiteral_2= '>' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '<' ) | (enumLiteral_5= '<=' ) | (enumLiteral_6= 'contains' ) )
            {
            // InternalAdaptationRules.g:1291:2: ( (enumLiteral_0= '==' ) | (enumLiteral_1= '!=' ) | (enumLiteral_2= '>' ) | (enumLiteral_3= '>=' ) | (enumLiteral_4= '<' ) | (enumLiteral_5= '<=' ) | (enumLiteral_6= 'contains' ) )
            int alt17=7;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt17=1;
                }
                break;
            case 34:
                {
                alt17=2;
                }
                break;
            case 35:
                {
                alt17=3;
                }
                break;
            case 36:
                {
                alt17=4;
                }
                break;
            case 37:
                {
                alt17=5;
                }
                break;
            case 38:
                {
                alt17=6;
                }
                break;
            case 39:
                {
                alt17=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // InternalAdaptationRules.g:1292:3: (enumLiteral_0= '==' )
                    {
                    // InternalAdaptationRules.g:1292:3: (enumLiteral_0= '==' )
                    // InternalAdaptationRules.g:1293:4: enumLiteral_0= '=='
                    {
                    enumLiteral_0=(Token)match(input,33,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getEQEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getComparisonOperatorAccess().getEQEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1300:3: (enumLiteral_1= '!=' )
                    {
                    // InternalAdaptationRules.g:1300:3: (enumLiteral_1= '!=' )
                    // InternalAdaptationRules.g:1301:4: enumLiteral_1= '!='
                    {
                    enumLiteral_1=(Token)match(input,34,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getNEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getComparisonOperatorAccess().getNEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalAdaptationRules.g:1308:3: (enumLiteral_2= '>' )
                    {
                    // InternalAdaptationRules.g:1308:3: (enumLiteral_2= '>' )
                    // InternalAdaptationRules.g:1309:4: enumLiteral_2= '>'
                    {
                    enumLiteral_2=(Token)match(input,35,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getGTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getComparisonOperatorAccess().getGTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalAdaptationRules.g:1316:3: (enumLiteral_3= '>=' )
                    {
                    // InternalAdaptationRules.g:1316:3: (enumLiteral_3= '>=' )
                    // InternalAdaptationRules.g:1317:4: enumLiteral_3= '>='
                    {
                    enumLiteral_3=(Token)match(input,36,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getGEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getComparisonOperatorAccess().getGEEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalAdaptationRules.g:1324:3: (enumLiteral_4= '<' )
                    {
                    // InternalAdaptationRules.g:1324:3: (enumLiteral_4= '<' )
                    // InternalAdaptationRules.g:1325:4: enumLiteral_4= '<'
                    {
                    enumLiteral_4=(Token)match(input,37,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getLTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getComparisonOperatorAccess().getLTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalAdaptationRules.g:1332:3: (enumLiteral_5= '<=' )
                    {
                    // InternalAdaptationRules.g:1332:3: (enumLiteral_5= '<=' )
                    // InternalAdaptationRules.g:1333:4: enumLiteral_5= '<='
                    {
                    enumLiteral_5=(Token)match(input,38,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getLEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getComparisonOperatorAccess().getLEEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalAdaptationRules.g:1340:3: (enumLiteral_6= 'contains' )
                    {
                    // InternalAdaptationRules.g:1340:3: (enumLiteral_6= 'contains' )
                    // InternalAdaptationRules.g:1341:4: enumLiteral_6= 'contains'
                    {
                    enumLiteral_6=(Token)match(input,39,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getCONTAINSEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getComparisonOperatorAccess().getCONTAINSEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparisonOperator"


    // $ANTLR start "ruleActionType"
    // InternalAdaptationRules.g:1351:1: ruleActionType returns [Enumerator current=null] : ( (enumLiteral_0= 'SHOW_HINT' ) | (enumLiteral_1= 'CHANGE_HINT_LEVEL' ) | (enumLiteral_2= 'REPEAT_ACTIVITY' ) | (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' ) | (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' ) | (enumLiteral_5= 'INCREASE_DIFFICULTY' ) | (enumLiteral_6= 'DECREASE_DIFFICULTY' ) | (enumLiteral_7= 'SHOW_CODE_VIEW' ) | (enumLiteral_8= 'HIDE_CODE_VIEW' ) | (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' ) ) ;
    public final Enumerator ruleActionType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1357:2: ( ( (enumLiteral_0= 'SHOW_HINT' ) | (enumLiteral_1= 'CHANGE_HINT_LEVEL' ) | (enumLiteral_2= 'REPEAT_ACTIVITY' ) | (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' ) | (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' ) | (enumLiteral_5= 'INCREASE_DIFFICULTY' ) | (enumLiteral_6= 'DECREASE_DIFFICULTY' ) | (enumLiteral_7= 'SHOW_CODE_VIEW' ) | (enumLiteral_8= 'HIDE_CODE_VIEW' ) | (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' ) ) )
            // InternalAdaptationRules.g:1358:2: ( (enumLiteral_0= 'SHOW_HINT' ) | (enumLiteral_1= 'CHANGE_HINT_LEVEL' ) | (enumLiteral_2= 'REPEAT_ACTIVITY' ) | (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' ) | (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' ) | (enumLiteral_5= 'INCREASE_DIFFICULTY' ) | (enumLiteral_6= 'DECREASE_DIFFICULTY' ) | (enumLiteral_7= 'SHOW_CODE_VIEW' ) | (enumLiteral_8= 'HIDE_CODE_VIEW' ) | (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' ) )
            {
            // InternalAdaptationRules.g:1358:2: ( (enumLiteral_0= 'SHOW_HINT' ) | (enumLiteral_1= 'CHANGE_HINT_LEVEL' ) | (enumLiteral_2= 'REPEAT_ACTIVITY' ) | (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' ) | (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' ) | (enumLiteral_5= 'INCREASE_DIFFICULTY' ) | (enumLiteral_6= 'DECREASE_DIFFICULTY' ) | (enumLiteral_7= 'SHOW_CODE_VIEW' ) | (enumLiteral_8= 'HIDE_CODE_VIEW' ) | (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' ) )
            int alt18=10;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt18=1;
                }
                break;
            case 41:
                {
                alt18=2;
                }
                break;
            case 42:
                {
                alt18=3;
                }
                break;
            case 43:
                {
                alt18=4;
                }
                break;
            case 44:
                {
                alt18=5;
                }
                break;
            case 45:
                {
                alt18=6;
                }
                break;
            case 46:
                {
                alt18=7;
                }
                break;
            case 47:
                {
                alt18=8;
                }
                break;
            case 48:
                {
                alt18=9;
                }
                break;
            case 49:
                {
                alt18=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // InternalAdaptationRules.g:1359:3: (enumLiteral_0= 'SHOW_HINT' )
                    {
                    // InternalAdaptationRules.g:1359:3: (enumLiteral_0= 'SHOW_HINT' )
                    // InternalAdaptationRules.g:1360:4: enumLiteral_0= 'SHOW_HINT'
                    {
                    enumLiteral_0=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getSHOW_HINTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getActionTypeAccess().getSHOW_HINTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1367:3: (enumLiteral_1= 'CHANGE_HINT_LEVEL' )
                    {
                    // InternalAdaptationRules.g:1367:3: (enumLiteral_1= 'CHANGE_HINT_LEVEL' )
                    // InternalAdaptationRules.g:1368:4: enumLiteral_1= 'CHANGE_HINT_LEVEL'
                    {
                    enumLiteral_1=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getCHANGE_HINT_LEVELEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getActionTypeAccess().getCHANGE_HINT_LEVELEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalAdaptationRules.g:1375:3: (enumLiteral_2= 'REPEAT_ACTIVITY' )
                    {
                    // InternalAdaptationRules.g:1375:3: (enumLiteral_2= 'REPEAT_ACTIVITY' )
                    // InternalAdaptationRules.g:1376:4: enumLiteral_2= 'REPEAT_ACTIVITY'
                    {
                    enumLiteral_2=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getREPEAT_ACTIVITYEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getActionTypeAccess().getREPEAT_ACTIVITYEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalAdaptationRules.g:1383:3: (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' )
                    {
                    // InternalAdaptationRules.g:1383:3: (enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY' )
                    // InternalAdaptationRules.g:1384:4: enumLiteral_3= 'SELECT_REINFORCEMENT_ACTIVITY'
                    {
                    enumLiteral_3=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getSELECT_REINFORCEMENT_ACTIVITYEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getActionTypeAccess().getSELECT_REINFORCEMENT_ACTIVITYEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalAdaptationRules.g:1391:3: (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' )
                    {
                    // InternalAdaptationRules.g:1391:3: (enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT' )
                    // InternalAdaptationRules.g:1392:4: enumLiteral_4= 'ADVANCE_TO_NEXT_CONCEPT'
                    {
                    enumLiteral_4=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getADVANCE_TO_NEXT_CONCEPTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getActionTypeAccess().getADVANCE_TO_NEXT_CONCEPTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalAdaptationRules.g:1399:3: (enumLiteral_5= 'INCREASE_DIFFICULTY' )
                    {
                    // InternalAdaptationRules.g:1399:3: (enumLiteral_5= 'INCREASE_DIFFICULTY' )
                    // InternalAdaptationRules.g:1400:4: enumLiteral_5= 'INCREASE_DIFFICULTY'
                    {
                    enumLiteral_5=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getINCREASE_DIFFICULTYEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getActionTypeAccess().getINCREASE_DIFFICULTYEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalAdaptationRules.g:1407:3: (enumLiteral_6= 'DECREASE_DIFFICULTY' )
                    {
                    // InternalAdaptationRules.g:1407:3: (enumLiteral_6= 'DECREASE_DIFFICULTY' )
                    // InternalAdaptationRules.g:1408:4: enumLiteral_6= 'DECREASE_DIFFICULTY'
                    {
                    enumLiteral_6=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getDECREASE_DIFFICULTYEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getActionTypeAccess().getDECREASE_DIFFICULTYEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalAdaptationRules.g:1415:3: (enumLiteral_7= 'SHOW_CODE_VIEW' )
                    {
                    // InternalAdaptationRules.g:1415:3: (enumLiteral_7= 'SHOW_CODE_VIEW' )
                    // InternalAdaptationRules.g:1416:4: enumLiteral_7= 'SHOW_CODE_VIEW'
                    {
                    enumLiteral_7=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getSHOW_CODE_VIEWEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getActionTypeAccess().getSHOW_CODE_VIEWEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalAdaptationRules.g:1423:3: (enumLiteral_8= 'HIDE_CODE_VIEW' )
                    {
                    // InternalAdaptationRules.g:1423:3: (enumLiteral_8= 'HIDE_CODE_VIEW' )
                    // InternalAdaptationRules.g:1424:4: enumLiteral_8= 'HIDE_CODE_VIEW'
                    {
                    enumLiteral_8=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getHIDE_CODE_VIEWEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getActionTypeAccess().getHIDE_CODE_VIEWEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalAdaptationRules.g:1431:3: (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' )
                    {
                    // InternalAdaptationRules.g:1431:3: (enumLiteral_9= 'CHANGE_FEEDBACK_STYLE' )
                    // InternalAdaptationRules.g:1432:4: enumLiteral_9= 'CHANGE_FEEDBACK_STYLE'
                    {
                    enumLiteral_9=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getActionTypeAccess().getCHANGE_FEEDBACK_STYLEEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getActionTypeAccess().getCHANGE_FEEDBACK_STYLEEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActionType"


    // $ANTLR start "ruleHintLevel"
    // InternalAdaptationRules.g:1442:1: ruleHintLevel returns [Enumerator current=null] : ( (enumLiteral_0= 'CONCEPTUAL' ) | (enumLiteral_1= 'GUIDED' ) | (enumLiteral_2= 'DIRECT' ) ) ;
    public final Enumerator ruleHintLevel() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1448:2: ( ( (enumLiteral_0= 'CONCEPTUAL' ) | (enumLiteral_1= 'GUIDED' ) | (enumLiteral_2= 'DIRECT' ) ) )
            // InternalAdaptationRules.g:1449:2: ( (enumLiteral_0= 'CONCEPTUAL' ) | (enumLiteral_1= 'GUIDED' ) | (enumLiteral_2= 'DIRECT' ) )
            {
            // InternalAdaptationRules.g:1449:2: ( (enumLiteral_0= 'CONCEPTUAL' ) | (enumLiteral_1= 'GUIDED' ) | (enumLiteral_2= 'DIRECT' ) )
            int alt19=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt19=1;
                }
                break;
            case 51:
                {
                alt19=2;
                }
                break;
            case 52:
                {
                alt19=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalAdaptationRules.g:1450:3: (enumLiteral_0= 'CONCEPTUAL' )
                    {
                    // InternalAdaptationRules.g:1450:3: (enumLiteral_0= 'CONCEPTUAL' )
                    // InternalAdaptationRules.g:1451:4: enumLiteral_0= 'CONCEPTUAL'
                    {
                    enumLiteral_0=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getHintLevelAccess().getCONCEPTUALEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getHintLevelAccess().getCONCEPTUALEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1458:3: (enumLiteral_1= 'GUIDED' )
                    {
                    // InternalAdaptationRules.g:1458:3: (enumLiteral_1= 'GUIDED' )
                    // InternalAdaptationRules.g:1459:4: enumLiteral_1= 'GUIDED'
                    {
                    enumLiteral_1=(Token)match(input,51,FOLLOW_2); 

                    				current = grammarAccess.getHintLevelAccess().getGUIDEDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getHintLevelAccess().getGUIDEDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalAdaptationRules.g:1466:3: (enumLiteral_2= 'DIRECT' )
                    {
                    // InternalAdaptationRules.g:1466:3: (enumLiteral_2= 'DIRECT' )
                    // InternalAdaptationRules.g:1467:4: enumLiteral_2= 'DIRECT'
                    {
                    enumLiteral_2=(Token)match(input,52,FOLLOW_2); 

                    				current = grammarAccess.getHintLevelAccess().getDIRECTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getHintLevelAccess().getDIRECTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHintLevel"


    // $ANTLR start "ruleFeedbackStyle"
    // InternalAdaptationRules.g:1477:1: ruleFeedbackStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'CONCISE' ) | (enumLiteral_1= 'EXPLANATORY' ) ) ;
    public final Enumerator ruleFeedbackStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalAdaptationRules.g:1483:2: ( ( (enumLiteral_0= 'CONCISE' ) | (enumLiteral_1= 'EXPLANATORY' ) ) )
            // InternalAdaptationRules.g:1484:2: ( (enumLiteral_0= 'CONCISE' ) | (enumLiteral_1= 'EXPLANATORY' ) )
            {
            // InternalAdaptationRules.g:1484:2: ( (enumLiteral_0= 'CONCISE' ) | (enumLiteral_1= 'EXPLANATORY' ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==53) ) {
                alt20=1;
            }
            else if ( (LA20_0==54) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // InternalAdaptationRules.g:1485:3: (enumLiteral_0= 'CONCISE' )
                    {
                    // InternalAdaptationRules.g:1485:3: (enumLiteral_0= 'CONCISE' )
                    // InternalAdaptationRules.g:1486:4: enumLiteral_0= 'CONCISE'
                    {
                    enumLiteral_0=(Token)match(input,53,FOLLOW_2); 

                    				current = grammarAccess.getFeedbackStyleAccess().getCONCISEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFeedbackStyleAccess().getCONCISEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalAdaptationRules.g:1493:3: (enumLiteral_1= 'EXPLANATORY' )
                    {
                    // InternalAdaptationRules.g:1493:3: (enumLiteral_1= 'EXPLANATORY' )
                    // InternalAdaptationRules.g:1494:4: enumLiteral_1= 'EXPLANATORY'
                    {
                    enumLiteral_1=(Token)match(input,54,FOLLOW_2); 

                    				current = grammarAccess.getFeedbackStyleAccess().getEXPLANATORYEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFeedbackStyleAccess().getEXPLANATORYEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeedbackStyle"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000800040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000340020L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000008080000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000300020L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000FE00000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000006800050L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0003FF0000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x001C000000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0060000000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000010000002L});

}