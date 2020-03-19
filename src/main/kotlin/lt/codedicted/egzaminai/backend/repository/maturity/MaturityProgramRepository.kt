package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityProgramRepository  : MongoRepository<MaturityProgram, ObjectId>
