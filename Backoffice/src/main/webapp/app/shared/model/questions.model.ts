import { IPropositions } from 'app/shared/model/propositions.model';
import { IReponses } from 'app/shared/model/reponses.model';

export interface IQuestions {
  id?: number;
  idquestion?: number;
  question?: string;
  propositions?: IPropositions[];
  reponses?: IReponses[];
}

export const defaultValue: Readonly<IQuestions> = {};
