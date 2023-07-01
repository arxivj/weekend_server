package com.arxivj.weekend_server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"first","last"})
public class Name { // Outer class

    @NotEmpty
    @Column(name = "first_name", length = 50)
    private String first;

    @NotEmpty
    @Column(name = "last_name", length = 50)
    private String last;


    public Name(final String first, final String last){
        this.first = first;
        this.last = last;
    }

    /* @Builder 구현 */
    public static class NameBuilder {   // Static inner class
        private String first;
        private String last;
        public NameBuilder first(String first){
            this.first = first;
            return this;
        }
        public NameBuilder last(String last){
            this.last = last;
            return this;
        }
        public Name build(){
            return new Name(first, last);
        }
        public static NameBuilder builder(){
            return new NameBuilder();
        }
    }
    /* @Builder 끝 */

    public String getFullName(){
        return String.format("%s%s", this.last,this.first);
    }

}
