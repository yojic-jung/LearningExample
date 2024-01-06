package com.yojic.springstudy.toby.chap1.di

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CircularReferenceB {
    @Autowired
    lateinit var circularReferenceA: CircularReferenceA
}