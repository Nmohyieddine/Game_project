import { IReponses } from 'app/shared/model/reponses.model';

export interface IQuestions {
  id?: number;
  idquestion?: number;
  question?: string;
  reponses?: IReponses[];
}

export const defaultValue: Readonly<IQuestions> = {};
