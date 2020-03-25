package lt.codedicted.egzaminai.backend.repository.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppExamRepository: MongoRepository<PuppExam, ObjectId>