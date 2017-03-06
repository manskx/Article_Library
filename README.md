# README #

This repo contains a demo for CRUD operations and search for a string using Solr.
Containing the full loop from the database and restful apis to front end and consuming restful services.

### Technologies ###

* Java8
* Maven
* Solr6.3
* Hibernate ORM
* Jersey RESTful framework
* Testing tools ( junit, jersey test, hamcrest )
* Bootstrap
* Jquery
* Mustache

### Main workflow ###

* Storing data in MySql database
* Importing data to Solr
* Provide restful APIs
* Consuming restful APIs from frontend
* providing endpoints for:(add - read - edit - delete - search )
* Search is dymanic as you can search for a part of string EX: "data" matches "database"
* Search is smart, text in title is more important than to be in body and has higher score to be shown first.

### Contents ###

* Article: main eclipse project folder
* solr-6.3.0: complete solr search engine contains article_library core and configurations 
* Examples: contains screen	 shots and API examples
* article_library_Article.sql:  database schema for article table
* api_documentation: contains documentation for restful apis

### Setup ###

* create database schema - sql file provided
* import "Article" eclipse project and configure project test database connection
* deploy "Article" on server
* sonfigure solr and run solr using this command: /solr-6.3.0/bin/solr/ start 
* test restful APIs using this url: /Article/api/articles
* open frontend page /Article/articles.html

### Important Configurations ###

* database server in this file: hibernate.cfg.xml
* solr configurations in this file: SolrConfigurations.java
* solr core configuration:
	- database connection for importing: db-data-config.xml
	- solrconfig.xml --- setup dir

### Future Work ###

* adding more test cases
* adding delta import to solr

### Contact ###
* Ahmed Mansy
* ahmed.mansy156@gmail.com