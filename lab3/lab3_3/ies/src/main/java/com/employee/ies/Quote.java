package com.employee.ies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quote")
public class Quote {
    private String avaliacao;
    private long id;


    public Quote() {
    }

    
    public Quote(String quote, Movie movieById) {
        this.avaliacao=quote;
        this.order= movieById;
    }

   

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "avaliacao", nullable = false)
    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }


    


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    public Movie getOrder() {
        return order;
    }
    public void setOrder(Movie order) {
        this.order = order;
    }


    private Movie order;





    @Override
    public String toString() {
        return "Quote: id" + id + ", quote " + avaliacao + ", Movie " + order;
    }


}
