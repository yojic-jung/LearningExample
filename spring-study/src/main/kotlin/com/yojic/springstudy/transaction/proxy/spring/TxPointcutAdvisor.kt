package com.yojic.springstudy.transaction.proxy.spring

import org.aopalliance.aop.Advice
import org.springframework.aop.Pointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class TxPointcutAdvisor : DefaultPointcutAdvisor() {
    @Qualifier("nameMatcher")
    override fun setPointcut(pointcut: Pointcut?) {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedName("create*")
        super.setPointcut(pointcut)
    }

    @Qualifier("transaction")
    override fun setAdvice(advice: Advice) {
        super.setAdvice(advice)
    }
}
