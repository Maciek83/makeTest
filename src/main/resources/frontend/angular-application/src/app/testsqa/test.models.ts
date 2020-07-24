import { QuestionDisplayModel, QuestionDisplaySolveModel } from '../questions/question.models';

export interface TestDisplayModel{
    id:number,
    name:string,
    questionDisplayDto:QuestionDisplayModel[]
}

export interface TestDisplaySolveModel{
    id:number,
    name:string,
    questionDisplayDto:QuestionDisplaySolveModel[]
}

export class TestAddModel{
    constructor(public name: string, public questionsIds: number[]){}
}

export class ShareTestModel{
    constructor(public id: number){}
}