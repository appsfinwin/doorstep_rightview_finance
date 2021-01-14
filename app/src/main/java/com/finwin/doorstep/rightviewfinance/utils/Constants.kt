package com.finwin.doorstep.rightviewfinance.utils

class Constants {
    companion object{
        const val PREFERENCE_NAME: String = "PREFERENCE_NAME";
        const val BRANCH_ID: String="BRANCH_ID"
        const val BRANCH_NAME: String="BRANCH_NAME"
        const val USER_ID: String="USER_ID"
        const val USER_NAME: String="USER_NAME"
        const val AGENT_ID: String="USER_ID"
        const val AGENT_NAME: String="USER_NAME"
        const val CURRENT_BALANCE:String="CURRENT_BALANCE"
        const val DEPOSIT_AMOUNT: String="DEPOSIT_AMOUNT"
        const val DEPOSIT_DATE: String="DEPOSIT_DATE"
        const val TRANSACTION_ID: String="TRANSACTION_ID"
        const val OLD_BALANCE: String="OLD_BALANCE"
        const val ACCOUNT_NUMBER: String="ACCOUNT_NUMBER"
        const val PREVIOUS_BALANCE: String="PREVIOUS_BALANCE"
        const val NAME: String="NAME"
        const val MOBILE: String="MOBILE"
        const val FROM: String="FROM"
        const val CASH_DEPOSIT: String="CASH_DEPOSIT"
        const val BALANCE_ENQUIRY: String="BALANCE_ENQUIRY"
        const val JLG_FRAGMENT_STATUS: String="JLG_FRAGMENT_STATUS"
        var INTENT_RECIEPT_FROM_CASH_DEPOSIT:Int =1
        var INTENT_SEARCH_ACCOUNT_FROM_CASH_DEPOSIT:Int =2
        var INTENT_RECIEPT_FROM_CASH_WITHDRAWAL:Int =3
        var INTENT_SEARCH_ACCOUNT_FROM_CASH_WITHDRAWAL:Int =4
        var INTENT_SEARCH_ACCOUNT_FROM_MINI_STATEMENT:Int =5
        var INTENT_SEARCH_ACCOUNT_FROM_BALANCE_ENQUIRY:Int =6
        var INTENT_RECIEPT_FROM_BALANCE_ENQUIRY:Int =7
        var INTENT_SEARCH_GROUP:Int =8
        var INTENT_SEARCH_ACCOUNT_FROM_JLG:Int =9
    }
}