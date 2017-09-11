package com.paulosalgado.graphqlserver;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
	
	private static final long serialVersionUID = 1L;
	
	public GraphQLEndpoint() {
		super(buildSchema());
	}
	
	private static GraphQLSchema buildSchema() {
		
		LinkRepository linkRepository = new LinkRepository();
		
		return SchemaParser.newParser()
				.file("schema.graphqls")
				.resolvers(
						new Query(linkRepository), 
						new Mutation(linkRepository)
						)
				.build().makeExecutableSchema();
	}
	
}
