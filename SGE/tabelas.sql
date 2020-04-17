create database geseleicoes;

use geseleicoes;

create table eleitor(
		id int primary key,
        nome varchar(50) not null,
        cartaocidadao varchar(15) not null unique
        );
        
insert into eleitor (id, nome , cartaocidadao)
			values(1,'Pedro Oliveira','13826138');

insert into eleitor (id, nome, cartaocidadao)
			values(2,'William Souza','28675148');
            
insert into eleitor(id,nome,cartaocidadao)
			values(3,'Joao Monteiro','89675325');
            
 alter table eleitor ADD admin boolean;           
 
 select * from eleitor;
 
 update eleitor set admin = True where id=1;
 
  update eleitor set admin = False where id=2;
  
   update eleitor set admin = False where id=3;