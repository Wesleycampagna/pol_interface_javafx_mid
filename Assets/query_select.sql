-- query evidencia - substancia (com dimensão)
SELECT *FROM policia_db.evidencia
INNER JOIN policia_db.substancia ON (substancia.id_evidencia = evidencia.id_evidencia)
INNER JOIN policia_db.dimensao ON (substancia.id_dim = dimensao.id_dim);

-- query evidencia - arma
SELECT *FROM policia_db.evidencia
INNER JOIN policia_db.arma ON (evidencia.id_evidencia = arma.id_evidencia);

-- query evidencia - celular 
SELECT *FROM policia_db.evidencia
INNER JOIN policia_db.celular ON (evidencia.id_evidencia = celular.id_evidencia);

-- query evidencia - objeto 
SELECT *FROM policia_db.evidencia
INNER JOIN policia_db.objeto ON (evidencia.id_evidencia = objeto.id_evidencia);

-- query evidencia - veiculo 
SELECT *FROM policia_db.evidencia
INNER JOIN policia_db.veiculo ON (evidencia.id_evidencia = veiculo.id_evidencia);

-- query # evidencia
SELECT COUNT(*) FROM policia_db.evidencia; 

-- query # '<seu tipo de evidencia>'
-- select count('<seu tipo de evidencia>'.id_evidencia) from policia_db.'<seu tipo de evidencia>';

-- query delegado 
SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento,
policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai
FROM policia_db.delegado
JOIN policia_db.policial ON (policial.numero_matricula = delegado.numero_matricula)
JOIN policia_db.Pessoa ON (Pessoa.cpf = policial.cpf);

-- query nacionalidades de um individuo dado seu numero de cpf
SELECT nacionalidade.id_nacionalidade, nacionalidade.nacionalidade FROM policia_db.nac_pessoa
JOIN policia_db.nacionalidade ON (nac_pessoa.id_nacionalidade = nacionalidade.id_nacionalidade)
WHERE nac_pessoa.cpf = 03403403040;

-- select naturalidade (pessoa > cidadão (nome), cidade, estado)
SELECT Pessoa.nome, cidade_estado.cidade, cidade_estado.estado FROM policia_db.cidadao
JOIN policia_db.Pessoa ON (Pessoa.cpf = cidadao.cpf)
JOIN policia_db.naturalidade ON (cidadao.naturalidade = naturalidade.id_natural)
JOIN policia_db.cidade_estado ON (cidade_estado.id_cidade_estado = naturalidade.cid_estado);

-- select by id evidencia 
SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento,
policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai
FROM policia_db.delegado
JOIN policia_db.policial ON (policial.numero_matricula = delegado.numero_matricula)
JOIN policia_db.Pessoa ON (Pessoa.cpf = policial.cpf)
WHERE Pessoa.nome LIKE 'l%';

-- por retornando tudo (evidencia)
SELECT * FROM policia_db.evidencia
LEFT JOIN policia_db.arma ON (evidencia.id_evidencia = arma.id_evidencia)
LEFT JOIN policia_db.celular ON (evidencia.id_evidencia = celular.id_evidencia)
WHERE evidencia.id_evidencia = 6;

-- select cidad'ao
SELECT Pessoa.nome, Pessoa.cpf, Pessoa.rg, Pessoa.data_nascimento, 
Pessoa.nome_mae, Pessoa.nome_pai, cidadao.alcunha, cidadao.telefone,
cidadao.status, cidadao.naturalidade
FROM policia_db.cidadao, policia_db.Pessoa 
WHERE Pessoa.cpf = 49324102302 AND cidadao.cpf = Pessoa.cpf;

-- select pessoas com nacionalidade
SELECT nacionalidade.id_nacionalidade, nacionalidade.nacionalidade, Pessoa.nome, Pessoa.cpf FROM policia_db.nac_pessoa
JOIN policia_db.nacionalidade ON (nac_pessoa.id_nacionalidade = nacionalidade.id_nacionalidade)
JOIN policia_db.Pessoa ON ( nac_pessoa.cpf = Pessoa.cpf);

-- buscar naturalidade passado um id
SELECT cidade_estado.cidade, cidade_estado.estado FROM policia_db.naturalidade
JOIN policia_db.cidade_estado ON (naturalidade.cid_estado = cidade_estado.id_cidade_estado)
WHERE naturalidade.id_natural = 4;

-- query delegacia + ocorrencia
SELECT ocorrencia.data, ocorrencia.hora, ocorrencia.infracao,
ocorrencia.status , ocorrencia.del_responsavel,
ocorrencia.id_endereco, ocorrencia.delegacia, delegacia.sigla, 
delegacia.nome FROM policia_db.ocorrencia
JOIN policia_db.delegacia ON (delegacia.id_delegacia = ocorrencia.delegacia)
WHERE ocorrencia.id_ocorrencia = 1;

-- select matricula de um policial 
SELECT nro_matricula FROM policia_db.equipe
WHERE equipe.id_ocorrencia = 1;

-- update delegacia
UPDATE policia_db.ocorrencia
SET ocorrencia.delegacia = 1
WHERE ocorrencia.id_ocorrencia = 1;

-- quantidade ocorrências 
SELECT MAX(ocorrencia.id_ocorrencia) FROM policia_db.ocorrencia;

-- endereco
SELECT endereco.logradouro, endereco.numero, endereco.bairro, endereco.complemento, 
endereco.cep, endereco.referencia, cidade_estado.cidade,
cidade_estado.estado FROM policia_db.endereco
JOIN policia_db.cidade_estado ON (cidade_estado.id_cidade_estado = endereco.cid_est);

-- endereco by ID
SELECT endereco.logradouro, endereco.numero, endereco.bairro, endereco.complemento, 
endereco.cep, endereco.referencia, cidade_estado.cidade,
cidade_estado.estado FROM policia_db.endereco
JOIN policia_db.cidade_estado ON (cidade_estado.id_cidade_estado = endereco.cid_est)
WHERE endereco.id_endereco = 1;

-- update ocorrencia 
update policia_db.ocorrencia 
set ocorrencia.del_responsavel = 45345345300, ocorrencia.infracao = 'ma fé',
ocorrencia.status = 'ativo', ocorrencia.id_endereco = 4, ocorrencia.delegacia = 3
Where ocorrencia.id_ocorrencia = 5;

SELECT policial.numero_matricula, Pessoa.nome, Pessoa.rg, Pessoa.cpf, Pessoa.data_nascimento, policial.telefone, Pessoa.nome_mae, Pessoa.nome_pai FROM policia_db.policial JOIN policia_db.delegado ON (delegado.numero_matricula = policial.numero_matricula) JOIN policia_db.Pessoa ON (Pessoa.cpf = policial.cpf);
