//package com.yojic.springstudy.jdbc
//
//import org.h2.jdbcx.JdbcDataSource
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.jdbc.core.JdbcTemplate
//import javax.sql.DataSource
//
//@Configuration
//class JdbcConfig {
//    @Bean
//    fun dataSource(): DataSource {
//        val dataSource = JdbcDataSource()
//        dataSource.setURL("jdbc:h2:mem:testdb")
//        dataSource.user = "sa"
//        dataSource.password = ""
//        return dataSource
//    }
//
//    @Bean
//    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
//        return JdbcTemplate()
//    }
//}